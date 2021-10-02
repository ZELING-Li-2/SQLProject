import io
import sys
import os
nameRead = open("data/principals.csv","r",encoding = "utf-8")
nameWrite = open("data/test.csv","w", encoding = "utf-8")
nameWrite.write("Update")
nameWrite.write("\n")

nameWrite.write("DROP TABLE IF EXISTS principals;")
nameWrite.write("\n")

instantiate_string = "CREATE TABLE principals(titleId text,nconst text,category text, job text, characters text, PRIMARY KEY(nconst));"
nameWrite.write(instantiate_string)
nameWrite.write("\n")
nameWrite.write("INSERT INTO principals(titleID,nconst,category,job,characters) ")
nameWrite.write("Values ")
 
principalsdict = {}
iter = 0


for line in nameRead:

    if (iter != 0):
        values = line.split("\t")
       #print(values)
        try:
            principalsdict[values[2]] = {} #nconst
            principalsdict[values[2]]["titleID"]  = values[1]
            principalsdict[values[2]]["category"]  = values[3]
            principalsdict[values[2]]["job"]  = values[4]
            principalsdict[values[2]]["characters"]  = values[5].rstrip()
            lastkey = values[2] #will be the last key
        except:#there are undefined characters
            
            input("char problem?")
            
    iter += 1
def par(string):#add escape to apostrophes
    newstring = ""
    if (string == None): #make it blank
        string = "" 
    for chara in string:
        if (chara == "'"):
            newstring += "''"#special escape character

        elif (chara == "\""):
            newstring += "" #strip quotation marks?
        else:
            newstring += chara

    return newstring
    
iter = 0
lastkey = list(principalsdict.keys())[-1]
for nconst in principalsdict:
    #print(principalsdict[nconst])
    #input("pause")
    iter += 1
    writestring = ""
    nconststring = par(str(nconst))
    #print(nconststring)
    titleIDstring = par(str(principalsdict[nconst]["titleID"]))
    #print(titleIDstring)
    categorystring = par(str(principalsdict[nconst]["category"]))
    #print(categorystring)
    jobstring = par(str(principalsdict[nconst]["job"]))
    #print(jobstring)
    charactersstring = par(str(principalsdict[nconst]["characters"]))
    #print(charactersstring)
    
    #nameWrite.write("INSERT INTO principals(titleID,nconst,category,job,characters) ")
    #nameWrite.write("Values ")
    
    

    writestring +=  "(\'" + titleIDstring + "\'" + "," 
    writestring += "\'" + nconststring + "\'" + ","
    writestring += "\'" + categorystring + "\'" + ","
    writestring +=  "\'" + jobstring + "\'" + ","
    writestring += "\'" + charactersstring + "\'"
    writestring += ")"
    
    #print(writestring)
    #input("Pause")
    
    
    if (nconst != lastkey):
        writestring += ","
    
    nameWrite.write(writestring)
    
    #nameWrite.write("\n")
   
nameWrite.write(";")






nameWrite.close()
nameRead.close()
