'''
corsUtils.py

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

class CorsUtils(object):
    '''
    This class contains utilities methods related to "Cross Origin Resource Sharing (CORS)" web features.
    
    @author: Dominique RIGHETTO (dominique.righetto@owasp.org)     
    '''
                 
    def __init__(self):
        '''
        Constructor.
        '''
        #Keys representing CORS headers for manipulations.
        self.AccessControlAllowOrigin = "ACCESS-CONTROL-ALLOW-ORIGIN"
        self.AccessControlAllowMethods = "ACCESS-CONTROL-ALLOW-METHODS"
        self.AccessControlAllowHeaders = "ACCESS-CONTROL-ALLOW-HEADERS"
        self.AccessControlAllowMaxAge = "ACCESS-CONTROL-ALLOW-MAX-AGE"
        self.AccessControlAllowCredentials = "ACCESS-CONTROL-ALLOW-MAX-CREDENTIALS"        
        
    def providesCorsFeatures(self, freq, url_opener):
        '''
        Method to detect if url provides CORS features.
        
        @param freq: A fuzzableRequest object.
        @param url_opener: "core.data.url.xUrllib" class instance to use for HTTP request/response processing.
        @return: True if the url provides CORS features, False otherwise. 
        '''        
        #Initialize detection result flag
        supportsCors = False
        
        #Build HEAD HTTP request to perform detection
        url = freq.getURL()
        forgedReq = "HEAD " + url.getPath() + " HTTP/1.1\r\n"
        forgedReq = forgedReq + "Host: " + url.getDomain() + ":" + str(url.getPort()) + "\r\n"
        forgedReq = forgedReq + "User-Agent: W3AF\r\n"
        forgedReq = forgedReq + "Accept: */*\r\n"
        forgedReq = forgedReq + "Accept-Language: en-us,en;q=0.5\r\n"
        forgedReq = forgedReq + "Accept-Charset: ISO-8859-1,utf-8;q=0.7,*;q=0.7\r\n"
        #Sent request and analyze response
        #--Sent request            
        response = url_opener.sendRawRequest(forgedReq, "")
        #--Analyse response (explicitly do not care about response code)         
        for headerName in response.getHeaders().keys():
            if headerName.upper().strip() == self.AccessControlAllowOrigin:  
                supportsCors = True
                break
        
        #Return detection result flag
        return supportsCors             
       
       
    def retrieveCorsResponseHeaderValue(self, response, key):   
        '''
        Method to retrieve a CORS header value from a HTTP response.
        
        @param response: A httpResponse object.
        @param key: A corsUtils.XXXXX provided key representing the desired header value to retrieve.
        @return: The header value or None if the header do not exists. 
        '''        
        for headerName in response.getHeaders().keys():
            if headerName.upper().strip() == key:                
                return response.getHeaders()[headerName]
                break      
        return None
    
    
    def buildCorsGetHttpRequest(self, urlInfos, originHeaderValue):
        '''
        Method to generate a "GET" CORS HTTP request based on input context. 

        @parameter urlInfos: A url_object object.
        @parameter originHeaderValue: Value of the "ORIGIN" HTTP request header (if value is set to None then the "ORIGIN" header is skipped).
        @return: HTTP request as string.
        '''        
        forgedReq = "GET " + urlInfos.getPath() + " HTTP/1.1\r\n"
        forgedReq = forgedReq + "Host: " + urlInfos.getDomain() + ":" + str(urlInfos.getPort()) + "\r\n"
        forgedReq = forgedReq + "User-Agent: W3AF\r\n"
        forgedReq = forgedReq + "Accept: text/html,application/xhtml+xml,application/xml;q=0.9,*/*;q=0.8\r\n"
        forgedReq = forgedReq + "Accept-Language: en-us,en;q=0.5\r\n"
        forgedReq = forgedReq + "Accept-Charset: ISO-8859-1,utf-8;q=0.7,*;q=0.7\r\n"
        if(originHeaderValue != None):
            forgedReq = forgedReq + "Origin: " + originHeaderValue.strip() + "\r\n"           
        return forgedReq
