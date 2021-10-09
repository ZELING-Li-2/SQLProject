import io
import sys
import os
nameRead = open("data/titles.csv","r",encoding = "utf-8")
nameWrite = open("data/test.csv","w", encoding = "utf-8")
nameWrite.write("Update")
nameWrite.write("\n")

nameWrite.write("DROP TABLE IF EXISTS titles;")
nameWrite.write("\n")

#instantiate_string = "CREATE TABLE titles (titleId text, titleType text, originalTitle text, startYear integer, endYear integer, runtimeMinutes integer, genres text, year integer, averageRating decimal, numVotes integer, PRIMARY KEY (titleId));"
instantiate_string = "CREATE TABLE titles (titleId text, titleType text, originalTitle text, runtimeMinutes integer, genres text, year integer, averageRating decimal, Views integer, PRIMARY KEY (titleId));"
nameWrite.write(instantiate_string)
nameWrite.write("\n")
#nameWrite.write("INSERT INTO titles(titleId,titleType,originalTitle,startYear,endYear,runtimeMinutes,genres,year,averageRating,numVotes) ")
nameWrite.write("INSERT INTO titles(titleId,titleType,originalTitle,runtimeMinutes,genres,year,averageRating, Views) ")
nameWrite.write("Values ")
 
titlesdict = {}
iter = 0


for line in nameRead:

    if (iter != 0):
        values = line.split("\t")
       #print(values)
        try:
            titlesdict[values[1]] = {} #titleId
            titlesdict[values[1]]["titleType"]  = values[2]
            titlesdict[values[1]]["originalTitle"]  = values[3]
            titlesdict[values[1]]["startYear"]  = values[4]
            titlesdict[values[1]]["endYear"]  = values[5]
            titlesdict[values[1]]["runtimeMinutes"] = values[6]
            titlesdict[values[1]]["genres"] = values[7]
            titlesdict[values[1]]["year"] = values[8]
            titlesdict[values[1]]["averageRating"] = values[9]
            titlesdict[values[1]]["numVotes"] = values[10].rstrip()
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
for titleId in titlesdict:
    iter += 1
    writestring = ""
    titleIdstring = par(str(titleId))

    titleTypestring = par(str(titlesdict[titleId]["titleType"]))
    if (len(titleTypestring) == 0):
        titleTypestring = " "

    originalTitlestring = par(str(titlesdict[titleId]["originalTitle"]))
    if (len(originalTitlestring) == 0):
        originalTitlestring = " "

    startYearstring = par(str(titlesdict[titleId]["startYear"]))
    try:
        a = int(startYearstring)
    except:
        startYearstring = "0000" #if startyear is missing default int of 000 is added to table. presumably, nobody will think we mean that someone working in the film industry was born in the year 0
    
    endYearstring = par(str(titlesdict[titleId]["endYear"]))
    try:
        a = int(endYearstring)
    except:
        endYearstring = "0000"
    
    runtimeMinutesstring = par(str(titlesdict[titleId]["runtimeMinutes"]))
    try:
        a = int(runtimeMinutesstring)
    except:
        runtimeMinutesstring = "000"
    
    genresstring = par(str(titlesdict[titleId]["genres"]))
    if (len(genresstring) == 0):
        genresstring = " "
    
    yearstring = par(str(titlesdict[titleId]["year"]))
    try:
        a = int(yearstring)
    except:
        yearstring = "0000"
    
    averageRatingstring = par(str(titlesdict[titleId]["averageRating"]))
    try:
        a = float(averageRating)
    except:
        averageRating = "0.0"
    
    numVotesstring = par(str(titlesdict[titleId]["numVotes"]))
    try:
        a = int(numVotesstring)
    except:
        numVotesstring = "000"


    writestring += "(\'" + titleIdstring + "\'" + "," + "\'" + titleTypestring + "\'" + ","
    writestring +=  "\'" + originalTitlestring + "\'" + ","# + "\'" + startYearstring + "\'" + "," 
    writestring += "\'" + runtimeMinutesstring + "\'" + ","#"\'" + endYearstring +"\'" + "," + "\'"+ runtimeMinutesstring + "\'" + ","
    writestring += "\'" + genresstring + "\'" + ","
    writestring += "\'" + yearstring + "\'" +"," + "\'" + averageRatingstring + "\'" + ","
    writestring += "\'" + numVotesstring + "\'" + ")" 
    #writestring += "\'" + numVotesstring + "\'" + ")"
    if (titleId != lastkey):
        writestring += ","
    
    nameWrite.write(writestring)
nameWrite.write(";")






nameWrite.close()
nameRead.close()
