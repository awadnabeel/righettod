'''
inspectRequestPreflight.py
'''
from core.controllers.basePlugin.baseAuditPlugin import baseAuditPlugin
from core.data.options.optionList import optionList
from core.data.options.option import option
from core.data.constants import httpConstants
from core.controllers.w3afException import w3afException
import random
import core.controllers.outputManager as om
import core.data.kb.knowledgeBase as kb
import core.data.kb.vuln as vuln
import core.data.constants.severity as severity



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
        #Use HTTP class provided by W3AF framework : "core.data.url.xUrllib"
        try:         
            #Build request content
            forgedReqBody = '{"Content":"W3AF Test From inspectRequestPreflight plugin"}'
            forgedReq = self.testHttpMethod + " " + url.getPath() + " HTTP/1.1\r\n"
            forgedReq = forgedReq + "Host: " + url.getDomain() + ":" + str(url.getPort()) + "\r\n"
            forgedReq = forgedReq + "User-Agent: W3AF\r\n"
            forgedReq = forgedReq + "Accept: text/html,application/xhtml+xml,application/xml;q=0.9,*/*;q=0.8\r\n"
            forgedReq = forgedReq + "Accept-Language: en-us,en;q=0.5\r\n"
            forgedReq = forgedReq + "Accept-Charset: ISO-8859-1,utf-8;q=0.7,*;q=0.7\r\n"
            forgedReq = forgedReq + "Origin: " + self.originHeaderValue.strip() + "\r\n"  
            forgedReq = forgedReq + "Content-Type: application/json\r\n"  
            forgedReq = forgedReq + "Content-Length: " + str(len(forgedReqBody)) + "\r\n"  
            forgedReq = forgedReq + "X-W3AF-PLUGIN: inspectRequestPreflight\r\n" 
            #Sent request and analyze response
            #--Sent request
            response = self._uri_opener.sendRawRequest(forgedReq, forgedReqBody, False)
            responseCode = response.getCode()                    
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
        except Exception, e:
            #Manage error       
            om.out.error('Error in audit.inspectRequestPreflight: "' + repr(e) + '".')       
        
    def getOptions(self):
        '''
        @return: A list of option objects for this plugin.
        '''
        ol = optionList()
        d1 = "Origin HTTP header value in order to create a CORS request type"
        h1 = "Define value used to specify the 'Origin' HTTP header in order to create a CORS request type"
        o1 = option('originHeaderValue', self.originHeaderValue, d1, "string", help=h1)        
        ol.add(o1)        
        d2 = "Expected HTTP response code from application if it detect a request that must be preflighted but have not respected the preflight process defined by W3C specification"
        h2 = "Define the HTTP response code that the application return if it detect that a request that must be preflighted but have not respected the preflight process defined by W3C specification"
        o2 = option('expectedHttpResponseCode', self.expectedHttpResponseCode, d2, "integer", help=h2)        
        ol.add(o2)
        d3 = "HTTP method to use for the request that will be forged to test the application behavior"
        h3 = "Define the HTTP method to use for the request that will be forged to test the application behavior (value must be 'POST' or 'DELETE' or 'PUT')"
        o3 = option('testHttpMethod', self.testHttpMethod, d3, "string", help=h3)        
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
            - originHeaderValue
            - expectedHttpResponseCode      
            - testHttpMethod
      
        Note        : This plugin is useful to test "Cross Origin Resource Sharing (CORS)" application behaviors.
        
        Preflighted : http://developer.mozilla.org/en-US/docs/HTTP_access_control#Preflighted_requests
          requests    http://www.w3.org/TR/cors/#cross-origin-request-with-preflight-0
                                       
        CORS        : http://developer.mozilla.org/en-US/docs/HTTP_access_control
                      http://www.w3.org/TR/cors
        '''
