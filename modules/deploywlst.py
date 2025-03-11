print 'Start configuring...'

url='t3://' + sys.argv[1] + ':' + sys.argv[2]
username = sys.argv[3]
password = sys.argv[4]
serverName = sys.argv[5]
appname = sys.argv[6]
path = sys.argv[7]
depordervalue = sys.argv[8]

try:
    connect(username, password, url)
except:
    print "Error while trying to connect server, please make sure server is running."

edit()
startEdit()
deploy(appname,path,serverName, deploymentOrder=depordervalue,securityModel='DDOnly')
activate()
disconnect()