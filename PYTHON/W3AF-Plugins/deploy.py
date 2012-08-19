'''
Script to deploy plugins to a W3AF instance.

@author: Dominique RIGHETTO (dominique.righetto@owasp.org)
'''
import sys
from distutils import dir_util

#Retrieve W3AF instance location from command line paramter and copy plugins directory to target path...
if(len(sys.argv) != 2):
    print "The location of the target W3AF instance is required !"
    print "Usage: %s W3AF_LOCATION" % sys.argv[0]
    print "   Ex: %s /opt/w3af" % sys.argv[0]
    print "       %s D:/w3af" % sys.argv[0]
else:
    print "Deploying plugins..."
    w3afLocation = sys.argv[1] + "/plugins"
    dir_util.copy_tree("plugins", w3afLocation)
    print "Plugins deployed to: '%s'." % w3afLocation
        
