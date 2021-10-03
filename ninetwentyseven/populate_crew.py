import io
import sys
import os
crewRead = open("data/crew.csv","r",encoding = "utf-8")
crewWrite = open("data/test.csv","w", encoding = "utf-8")
crewWrite.write("Update")
crewWrite.write("\n")

crewWrite.write("DROP TABLE IF EXISTS crew;")
crewWrite.write("\n")

instantiate_string = "CREATE TABLE crew (titleId text, directors text, writers text, PRIMARY KEY (titleId));"
crewWrite.write(instantiate_string)
crewWrite.write("\n")
crewWrite.write("INSERT INTO crew(titleId, directors, writers) ")  # Inserting into table, the first statement
crewWrite.write("Values ")
 
crewdict = {}
iter = 0


for line in crewRead:

    if (iter != 0):
        values = line.split("\t")
       #print(values)
        try:
            crewdict[values[1]] = {} #titleId is the base/primary key
            crewdict[values[1]]["directors"]  = values[2]
            crewdict[values[1]]["writers"]  = values[3].rstrip()
            lastkey = values[1] #will be the last key
        except:#there are undefined characters
            
            input("char problem?")
            
    iter += 1
def par(string):#add escape to apostrophes
    newstring = ""
    for chara in string:
        if (chara == "'"):
            newstring += "''"
        else:
            newstring += chara
    return newstring
    
iter = 0
for titleId in crewdict:
    iter += 1
    writestring = ""
    titleId_string = par(str(titleId))
    director_tring = par(str(crewdict[titleId]["directors"]))
    writer_string = par(str(crewdict[titleId]["writers"]))

    writestring += "(\'" + titleId_string + "\'," 
    writestring += "\'" + director_tring + "\'," 
    writestring += "\'" + writer_string + "\'"
    writestring += ")"
    if (titleId != lastkey):
        writestring += ","
    
    crewWrite.write(writestring)
crewWrite.write(";")


crewWrite.close()
crewRead.close()
