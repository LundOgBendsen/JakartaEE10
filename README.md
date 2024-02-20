# JakartaEE10
Repo for LB course on Jakarta EE 10

# Prepare the setup for zip'ing or other exports

1. Remove all target-folders
1.1 Start Windows PowerShell CLI
1.2 Set-ExecutionPolicy -ExecutionPolicy Unrestricted
1.3 .\RemoveTargetFolders.ps1
1.4 Set-ExecutionPolicy Restricted

2. Delete app server stuff
2.1 Delete all from L:\appservers\wildfly-29.0.1.Final\standalone\deployments
2.2 Delete all from L:\appservers\wildfly-29.0.1.Final\standalone\data
2.3 Delete all from L:\appservers\wildfly-29.0.1.Final\standalone\tmp
2.4 Delete all from L:\appservers\wildfly-29.0.1.Final\standalone\log

3. Restore databases
3.1 L:\deleteDatabases.cmd
3.2 L:\restoreDatabases.cmd

4. Pull repo
4.1 cd l:\repo\JakartaEE10
4.2 git pull


