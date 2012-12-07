#########################################################
# Script to perform a Exploit search on PacketStorm site
# using command line.
# 
# Script have a dependency on Python module below:
# - feedparser : http://code.google.com/p/feedparser/
#
# Author : Dominique Righetto 
#          dominique.righetto@owasp.org
#########################################################
import feedparser
import sys
from termcolor import colored
#Check for search terms presence
if(len(sys.argv) != 2):
	print ""
	print colored("Syntax (use quote or double quotes to specify multiple terms):","yellow")
	print " %s [SEARCH_TERMS]" % sys.argv[0]
	print ""	
	print colored("Examples:","yellow")
	print " %s windows" % sys.argv[0]	
	print " %s 'oracle linux'" % sys.argv[0]
	print " %s \"oracle windows\"" % sys.argv[0]
	print ""
	sys.exit(-1)
#Build search query
search_terms = sys.argv[1].replace(" ","+").strip()
feed_url = "http://rss.packetstormsecurity.org/search/files/?s=files&q=" + search_terms
#Run search and get RSS feed
feed = feedparser.parse(feed_url)
#Parse result
if ((len(feed["items"]) == 0) or (len(feed["items"]) == 1 and feed["items"][0]["title"].lower() == "no results found")):
	print colored("No results found !","red")
else:
	separator = '-' * 140
	#Print header
	print ""	
	print colored(" %s exploits found." % len(feed["items"]),"green")
	print " Add prefix 'http://packetstormsecurity.org/files' to download link."
	print "" 	
	print separator	
	print " %-79s | %-20s" % ("SUMMARY", "DOWNLOAD LINK")
	print separator
	#Print result
	for item in feed["items"]:
		exploit_title = item["title"]
		exploit_download_link = item["guid"].replace("http://packetstormsecurity.org/files","").strip()		
		print "%-80s | %-20s" % (exploit_title, exploit_download_link)
	print separator
sys.exit(0)
