'''
inspectOriginHeaderScrutiny.py

Copyright 2012 Andres Riancho

This file is part of w3af, w3af.sourceforge.net .

w3af is free software; you can redistribute it and/or modify
it under the terms of the GNU General Public License as published by
the Free Software Foundation version 2 of the License.
    
w3af is distributed in the hope that it will be useful,
but WITHOUT ANY WARRANTY; without even the implied warranty of
MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
GNU General Public License for more details.

You should have received a copy of the GNU General Public License
along with w3af; if not, write to the Free Software
Foundation, Inc., 51 Franklin St, Fifth Floor, Boston, MA 02110-1301 USA
'''

from core.controllers.basePlugin.baseAuditPlugin import baseAuditPlugin
from core.data.options.optionList import optionList
from core.data.options.option import option
from core.data.constants import httpConstants
from core.controllers.w3afException import w3afException
from core.controllers.misc import corsUtils
import core.controllers.outputManager as om
import core.data.kb.knowledgeBase as kb
import core.data.kb.vuln as vuln
import core.data.constants.severity as severity

class inspectOriginHeaderScrutiny(baseAuditPlugin):
    '''
    Inspect if application check that the value of the "Origin" HTTP header is consistent with the value 
    of the remote IP address/Host of the sender of the incoming HTTP request.
      
    @author: Dominique RIGHETTO (dominique.righetto@owasp.org)     
    '''

    def __init__(self):
        '''
        Class constructor
        '''
        baseAuditPlugin.__init__(self)
        #Define plugin options configuration variables
        self.originHeaderValue = "http://w3af.sourceforge.net"
        self.expectedHttpResponseCode = httpConstants.OK   
        self.enableExtendedMode = False
        #Define utility class instance
        self.xrsUtils = corsUtils.CorsUtils()
        
    def audit(self, freq):
        '''
        Plugin entry point.

        @param freq: A fuzzableRequest
        ''' 

        #Detect if current url provides CORS features   
        if not self.xrsUtils.providesCorsFeatures(freq, self._uri_opener):
            return      
        
        #Get current URL object
        url = freq.getURL()
                
        #Try to send a forged HTTP request in order to test target application behavior
        #Use HTTP class provided by W3AF framework : "core.data.url.xUrllib"
        try:
            #Basic mode : Only one originator
            originatorList = [self.originHeaderValue]
            #Extended mode : Add more originator
            if(self.enableExtendedMode):
                originatorList.append(None)
                originatorList.append("http://www.google.com")
                originatorList.append("")
                originatorList.append("null")
                originatorList.append("*")
            #Perform check(s)
            accessControlAllowOriginHeaderValueList = []
            for originator in originatorList:    
                #-- Build request
                forgedReq = self.xrsUtils.buildCorsGetHttpRequest(url, originator)
                #-- Send forged request and retrieve response informations
                response = self._uri_opener.sendRawRequest(forgedReq, "")
                accessControlAllowOriginHeaderValue = self.xrsUtils.retrieveCorsResponseHeaderValue(response, self.xrsUtils.AccessControlAllowOrigin)
                responseCode = response.getCode()
                #-- Check response informations              
                if ((accessControlAllowOriginHeaderValue != "*") and (accessControlAllowOriginHeaderValue != originator) and (responseCode == self.expectedHttpResponseCode)):
                    v = vuln.vuln()
                    v.setSeverity(severity.MEDIUM)
                    v.setPluginName(self.getName())
                    v.setName('Inspect Origin Header Scrutiny')
                    v.setURL(url)
                    v.setId(response.id)
                    if(originator != None):
                        msg = 'Application seems to reply correctly event if it tampers with the "Origin" HTTP header using value "' + originator + '".'
                    else:
                        msg = 'Application seems to reply correctly event if we do not add a "Origin" HTTP header.'
                    if(accessControlAllowOriginHeaderValue != None):    
                        msg = msg + ' W3AF has received "' + accessControlAllowOriginHeaderValue + '" value for "AccessControlAllowOrigin" HTTP header.'
                    else:
                        msg = msg + ' W3AF has not find any occurence of "AccessControlAllowOrigin" HTTP header.'                        
                    v.setDesc(msg)
                    kb.kb.append(self , 'inspectOriginHeaderScrutiny' , v)    
                #-- Save current value of the "AccessControlAllowOriginHeaderValue" in order to perform an extra check on value variation at the end...
                accessControlAllowOriginHeaderValueList.append(accessControlAllowOriginHeaderValue)
            #Perform extra check on "AccessControlAllowOriginHeaderValue" value variation
            if(len(accessControlAllowOriginHeaderValueList) > 1 and self.enableExtendedMode):
                firstValue = accessControlAllowOriginHeaderValueList[0]
                if(accessControlAllowOriginHeaderValueList.count(firstValue) != len(accessControlAllowOriginHeaderValueList)):
                    v = vuln.vuln()
                    v.setSeverity(severity.MEDIUM)
                    v.setPluginName(self.getName())
                    v.setName('Inspect Origin Header Scrutiny')
                    v.setURL(url)
                    v.setId(response.id)
                    msg = 'Application HTTP response header "AccessControlAllowOrigin" value is different between HTTP requests.'
                    v.setDesc(msg)
                    kb.kb.append(self , 'inspectOriginHeaderScrutiny' , v)                       
        except Exception, e:
            #Manage error       
            om.out.error('Error in audit.inspectOriginHeaderScrutiny: "' + repr(e) + '".')
                                
    def getOptions(self):
        '''
        @return: A list of option objects for this plugin.
        '''
        ol = optionList()
        d1 = "Origin HTTP header value"
        h1 = "Define value used to specify the 'Origin' HTTP header for HTTP request sent to test application behavior"
        o1 = option('originHeaderValue', self.originHeaderValue, d1, "string", help=h1)        
        ol.add(o1)        
        d2 = "Expected HTTP response code from application when it have successfully processed a HTTP request"
        h2 = "Define the HTTP response code that the application return when it have successfully processed a HTTP request"
        o2 = option('expectedHttpResponseCode', self.expectedHttpResponseCode, d2, "integer", help=h2)        
        ol.add(o2)
        d3 = "Flag to enable the extended running mode of the plugin: Normal mode check application response against only one value of the 'Origin' header (value specified or default value) - Extended mode check application response against several value of the 'Origin' header (value specified or default value + others predefined values)."
        h3 = "Enable the extended running mode"
        o3 = option('enableExtendedMode', self.enableExtendedMode, d3, "boolean", help=h3)        
        ol.add(o3)         
        return ol

    def setOptions(self, optionList):
        '''
        This method sets all the options that are configured using the user interface 
        generated by the framework using the result of getOptions().usefull

        @parameter OptionList: A dictionary with the options for the plugin.
        @return: No value is returned.
        '''
        self.originHeaderValue = optionList['originHeaderValue'].getValue()
        self.expectedHttpResponseCode = optionList['expectedHttpResponseCode'].getValue()   
        self.enableExtendedMode = optionList['enableExtendedMode'].getValue()   
        #Check options setted
        if self.expectedHttpResponseCode < 100 or self.expectedHttpResponseCode > 505:
            msg = 'Please enter a valid HTTP response code (see valid code list on "http://www.w3.org/Protocols/rfc2616/rfc2616-sec10.html") !'
            raise w3afException(msg)
        if self.originHeaderValue is None or len(self.originHeaderValue.strip()) == 0 :
            msg = 'Please enter a valid value for the "Origin" HTTP header !'
            raise w3afException(msg)                                           

    def getPluginDeps(self):
        '''
        @return: A list with the names of the plugins that should be runned before the
        current one.
        '''
        return []

    def getLongDesc(self):
        '''
        @return: A DETAILED description of the plugin functions and features.
        '''
        return '''
        Inspect if application check that the value of the "Origin" HTTP header is consistent with the value 
        of the remote IP address/Host of the sender of the incoming HTTP request.
        
        Configurable parameters are:
            - originHeaderValue
            - expectedHttpResponseCode    
            - enableExtendedMode  
      
        Note : This plugin is useful to test "Cross Origin Resource Sharing (CORS)" application behaviors.
        CORS : http://developer.mozilla.org/en-US/docs/HTTP_access_control
               http://www.w3.org/TR/cors
        '''
