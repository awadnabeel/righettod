/*Application startup point*/
Ext.setup({
    tabletStartupScreen: 'resources/img/tablet_startup.png',
    phoneStartupScreen: 'resources/img/phone_startup.png',
    icon: 'resources/img/icon.png',
    glossOnIcon: false,
    /*Define WUI here in the 'onReady' function*/
    onReady: function() {
	
		/*Define template zone for ride content display*/
		var tpl = Ext.XTemplate.from('ride');
	
		/*Define a handler to manage button click (tap on mobile screen)*/
		var tapHandler = function(item) {
		    var param = item.title.toUpperCase().replace(" > ","");
		    var salt  = new Date().getMilliseconds(); 
		    //Make a AJAX call to get ride informations as SenchaTouch JSONP structure and update template using data received...
            Ext.util.JSONP.request({
                url: 'json.jsp',
                callbackKey: 'callback',
                params: {                    
                    ride: param,
                    salt: salt //In order to avoid browser caching...
                },
                callback: function(result) {
                    var ride = result;
                    if (ride) {
                    	//Update item content using the template with the data received
                        item.update(tpl.applyTemplate(ride));
                    }
                    else {
                    	item.update('There was an error retrieving the data.');                   	
                    }
                }
            })            
		};	
		
		/*Define the global UI */
        var tabpanel = new Ext.TabPanel({
            tabBar: {
                dock: 'bottom',
                layout: {
                    pack: 'center'
                }
            },
            fullscreen: true,
            ui: 'light',
            animation: {
                type: 'fade',
                cover: true
            },
            defaults: {
                scroll: 'vertical'
            },
            items: [{
            	 id: 'cardWelcome',
                 title: 'About',
                 html: '<h1>Tunnel Howald Buddy</h1><p>This application show informations about rides through <br /> the "Tunnel Howald" using the SenchaTouch framework.</p>',
                 iconCls: 'info',
                 cls: 'card'
            }, {
            	 id: 'cardA',
            	 title: 'A6 > A1',
                 iconCls: 'time',
                 cls: 'card',
                 listeners: {
                     activate: function () { tapHandler(this); }
             	 }
            }, {
            	id: 'cardB',
            	title: 'A1 > A6',
            	iconCls: 'time',
            	cls: 'card',
                listeners: {
                    activate: function () { tapHandler(this); }
            	 }            	
            }, {
            	id: 'cardC',
            	title: 'A3 > A1',
            	iconCls: 'time',
            	cls: 'card',
                listeners: {
                    activate: function () { tapHandler(this); }
            	 }            	
            }]
        });				               			
	}
});