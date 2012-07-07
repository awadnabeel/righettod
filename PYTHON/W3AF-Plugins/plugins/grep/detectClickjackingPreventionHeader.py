'''
detectClickjackingPreventionHeader.py
'''



from core.data.options.optionList import optionList
from core.controllers.basePlugin.baseGrepPlugin import baseGrepPlugin
import core.data.kb.knowledgeBase as kb
import core.data.kb.info as info
import core.data.kb.vuln as vuln
import core.data.constants.severity as severity

class detectClickjackingPreventionHeader(baseGrepPlugin):
    '''
    Grep header dedicated to Clickjacking prevention into HTTP responses.
      
    @author: Dominique RIGHETTO (dominique.righetto@owasp.org)    
    '''
    
    clickjacking_header_name = "X-FRAME-OPTIONS"
    
    def __init__(self):
        baseGrepPlugin.__init__(self)
                
    def setOptions(self, OptionList):
        pass
    
    def getOptions(self):
        '''
        @return: A list of option objects for this plugin.
        '''    
        ol = optionList()
        return ol    
    
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
        This plugin greps header dedicated to Clickjacking prevention into HTTP responses. 
        This could be usefull to identify if Clickjacking is managed by the applicatin at Header level.
        '''
    
    def grep(self, request, response):
        '''
        Plugin entry point.
        
        @parameter request: The HTTP request object.
        @parameter response: The HTTP response object
        @return: None, all results are saved in the kb.
        '''        
        # I search for header in HTTP response headers collection
        clickjacking_header_exists = False
        for header_name in response.getHeaders().keys():  
            # I iterate to catch all headers instance (i expect only one instance but i manage special cases)   
            if header_name.upper().strip() == self.clickjacking_header_name:                
                # I create a new information object by header instance from scratch and save it to the kb:
                i = info.info()
                i.setPluginName(self.getName())
                i.setName('Detect Clickjacking prevention header')
                i.setURL(response.getURL())
                i.setId(response.id)
                msg = 'The remote web server sent the Clickjacking prevention HTTP header: "' + header_name
                msg += '" with value: "' + response.getHeaders()[header_name] + '".'
                i.setDesc(msg)
                hvalue = response.getHeaders()[header_name]
                i['header_value'] = hvalue
                i.addToHighlight(hvalue, header_name)
                kb.kb.append(self , 'detectClickjackingPreventionHeader' , i)    
                clickjacking_header_exists = True           
                            
        # I manage case where the header is not sent
        if not clickjacking_header_exists:            
            # I create a new vulnerability object from scratch and save it to the kb:
            v = vuln.vuln()
            v.setSeverity(severity.MEDIUM)
            v.setPluginName(self.getName())
            v.setName('Detect Clickjacking prevention header')
            v.setURL(response.getURL())
            v.setId(response.id)
            msg = 'The remote web server do not sent the Clickjacking prevention HTTP header: "' + self.clickjacking_header_name + '".'
            v.setDesc(msg)
            hvalue = "N/A"
            v['header_value'] = hvalue
            v.addToHighlight(hvalue, self.clickjacking_header_name)
            kb.kb.append(self , 'detectClickjackingPreventionHeader' , v)            
        
        
    def end(self):
        '''
        This method is called when the plugin wont be used anymore.
        '''  
        # I retrieve info object collection from KB for this plugin run
        #headers = kb.kb.getData('detectClickjackingPreventionHeader', 'detectClickjackingPreventionHeader')   
        
