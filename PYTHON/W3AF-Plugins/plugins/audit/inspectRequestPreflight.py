'''
inspectRequestPreflight.py
'''
from core.controllers.basePlugin.baseAuditPlugin import baseAuditPlugin
from core.data.options.optionList import optionList
from core.data.options.option import option
from core.data.constants import httpConstants
from core.controllers.w3afException import w3afException
import urllib2
import random
import core.controllers.outputManager as om
import core.data.kb.knowledgeBase as kb
import core.data.kb.vuln as vuln
import core.data.constants.severity as severity
from urllib2 import HTTPError



class inspectRequestPreflight(baseAuditPlugin):
    '''
    Inspect if application include a server side check for incoming HTTP request that must be 
    preflighted in case of 'Cross Origin Resource Sharing (CORS)' request type.
      
    @author: Dominique RIGHETTO (dominique.righetto@owasp.org)     
    '''

    def __init__(self):
        '''
        Class constructor
        '''
        baseAuditPlugin.__init__(self)
        #Define plugin options configuration variables
        self.debug = False
        self.originHeaderValue = "http://w3af.sourceforge.net"
        self.expectedHttpResponseCode = httpConstants.FORBIDDEN
        self.testHttpMethod = "POST"
        
    def audit(self, freq):
        '''
        Plugin entry point.

        @param freq: A fuzzableRequest
        '''         
        #Get current URL object
        url = freq.getURL()
                
        #Try to send a forged HTTP request in order to test target application behavior
        try:
            #Build request content
            forgedReq = urllib2.Request(url.url_string)
            forgedReq.add_header("User-Agent", "W3AF")
            forgedReq.add_header("Accept", "text/html,application/xhtml+xml,application/xml;q=0.9,*/*;q=0.8")
            forgedReq.add_header("Accept-Language", "en-us,en;q=0.5")
            forgedReq.add_header("Accept-Charset", "ISO-8859-1,utf-8;q=0.7,*;q=0.7")
            forgedReq.add_header("Origin", self.originHeaderValue.strip())
            forgedReq.add_header("Content-Type", "application/json")
            forgedReq.add_header("X-W3AF-PLUGIN", "inspectRequestPreflight")            
            forgedReq.add_data('{"Content":"W3AF Test From inspectRequestPreflight plugin"}')
            forgedReq.get_method = lambda: self.testHttpMethod
            #Sent request and analyze response
            if(self.debug):
                dLevel = 1
            else:
                dLevel = 0  
            #--Sent request
            httpHandler = urllib2.HTTPHandler(debuglevel=dLevel)
            opener = urllib2.build_opener(httpHandler)
            response = opener.open(forgedReq)
            responseCode = response.getcode()
            opener.close() 
            #--Analyze response
            if responseCode != self.expectedHttpResponseCode:
                v = vuln.vuln()
                v.setSeverity(severity.MEDIUM)
                v.setPluginName(self.getName())
                v.setName('Inspect Request Preflight')
                v.setURL(url)
                v.setId(random.randint(1, 100) * random.randint(1, 100))
                msg = 'Application seems to accept the ' + self.testHttpMethod + ' request type even if an OPTIONS request type has not be previously sent to preflight the current request.'
                v.setDesc(msg)
                kb.kb.append(self , 'inspectRequestPreflight' , v)      
        except HTTPError, e:
            #Manage case where application return an HTTP error response code.
            #Case can exists where the expected reponse code is an error response code when the applicatin detect a non preflighted request.
            #In this case the 'urllib2' (default HTTP error handler) throw an HTTPError 
            #then we check if the response code correspond the to the one expected... 
            if e.code != self.expectedHttpResponseCode:
                om.out.error("The application have sent a HTTP response code '" + str(e.code) + "' for url '" + url.url_string + "'.")   
            #We do not log a vulnerability because the request has not been accepted by the application but we 
            #log some infos to show that the request has been managed by application....                                
        except Exception, e:
            #Manage error       
            om.out.error('Error in audit.inspectRequestPreflight: "' + repr(e) + '".')       
        
    def getOptions(self):
        '''
        @return: A list of option objects for this plugin.
        '''
        ol = optionList()
        d1 = "Debug mode"
        h1 = "If set to True, w3af will print HTTP request/response exchanges with target application"
        o1 = option('debug', self.debug, d1, "boolean", help=h1)        
        ol.add(o1)
        d2 = "Origin HTTP header value in order to create a CORS request type"
        h2 = "Define value used to specify the 'Origin' HTTP header in order to create a CORS request type"
        o2 = option('originHeaderValue', self.originHeaderValue, d2, "string", help=h2)        
        ol.add(o2)        
        d3 = "Expected HTTP response code from application if it detect a request that must be preflighted but have not respected the preflight process defined by W3C specification"
        h3 = "Define the HTTP response code that the application return if it detect that a request that must be preflighted but have not respected the preflight process defined by W3C specification"
        o3 = option('expectedHttpResponseCode', self.expectedHttpResponseCode, d3, "integer", help=h3)        
        ol.add(o3)
        d4 = "HTTP method to use for the request that will be forged to test the application behavior"
        h4 = "Define the HTTP method to use for the request that will be forged to test the application behavior (value must be 'POST' or 'DELETE' or 'PUT')"
        o4 = option('testHttpMethod', self.testHttpMethod, d4, "string", help=h4)        
        ol.add(o4)           
        return ol

    def setOptions(self, optionList):
        '''
        This method sets all the options that are configured using the user interface 
        generated by the framework using the result of getOptions().usefull

        @parameter OptionList: A dictionary with the options for the plugin.
        @return: No value is returned.
        '''
        self.debug = optionList['debug'].getValue()
        self.originHeaderValue = optionList['originHeaderValue'].getValue()
        self.expectedHttpResponseCode = optionList['expectedHttpResponseCode'].getValue()   
        self.testHttpMethod = optionList['testHttpMethod'].getValue()   
        #Check options setted
        if self.expectedHttpResponseCode < 100 or self.expectedHttpResponseCode > 505:
            msg = 'Please enter a valid HTTP response code (see valid code list on "http://www.w3.org/Protocols/rfc2616/rfc2616-sec10.html") !'
            raise w3afException(msg)
        if self.originHeaderValue is None or len(self.originHeaderValue.strip()) == 0 :
            msg = 'Please enter a valid value for the "Origin" HTTP header !'
            raise w3afException(msg)  
        if self.testHttpMethod is None or (self.testHttpMethod.upper().strip() != 'POST' and self.testHttpMethod.upper().strip() != 'DELETE' and self.testHttpMethod.upper().strip() != 'PUT'):
            msg = 'Please enter a valid value from "POST","DELETE","PUT" list !'
            raise w3afException(msg)
        self.testHttpMethod = self.testHttpMethod.strip().upper()                        

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
        Inspect if application include a server side check for incoming HTTP request that must be 
        preflighted in case of 'Cross Origin Resource Sharing (CORS)' request type.
        
        Configurable parameters are:
            - debug
            - originHeaderValue
            - expectedHttpResponseCode      
            - testHttpMethod
      
        Note        : This plugin is useful to test "Cross Origin Resource Sharing (CORS)" application behaviors.
        
        Preflighted : http://developer.mozilla.org/en-US/docs/HTTP_access_control#Preflighted_requests
          requests    http://www.w3.org/TR/cors/#cross-origin-request-with-preflight-0
                                       
        CORS        : http://developer.mozilla.org/en-US/docs/HTTP_access_control
                      http://www.w3.org/TR/cors
        '''
