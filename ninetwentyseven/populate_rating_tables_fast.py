import io
import sys
import os
nameRead = open("data/customer_ratings.csv","r",encoding = "utf-8")
nameWrite = open("data/test.csv","w", encoding = "utf-8")
nameWrite.write("Update")
nameWrite.write("\n")

nameWrite.write("DROP TABLE IF EXISTS customer_ratings;")
nameWrite.write("\n")

instantiate_string = "CREATE TABLE customer_ratings (reviewId integer , customerId text, rating decimal, date date, titleId text, PRIMARY KEY (reviewId));"
nameWrite.write(instantiate_string)
nameWrite.write("\n")

nameWrite.write("INSERT INTO customer_ratings(reviewId,customerId,rating,date,titleId) ")
nameWrite.write("Values ")

ratingdict = {}
iter = 0


for line in nameRead:

    if (iter != 0):
        values = line.split("\t")
       #print(values)
        try:
            ratingdict[values[0]] = {} #reviewId
            ratingdict[values[0]]["customerId"] = values[1]
            ratingdict[values[0]]["rating"]  = values[2]
            ratingdict[values[0]]["date"]  = values[3]
            ratingdict[values[0]]["titleId"]  = values[4].rstrip()
            lastkey = values[0] #will be the last key
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
for reviewId in ratingdict:
    iter += 1
    writestring = ""
    reviewIdstring = par(str(reviewId))
    customerIdstring = par(str(ratingdict[reviewId]["customerId"]))

    ratingstring = par(str(ratingdict[reviewId]["rating"]))
    try:
        a = float(ratingstring)
    except:
        ratingstring = "0.0"

    datestring = par(str(ratingdict[reviewId]["date"]))
    if (len(datestring) == 0):
        datestring = "0/0/0000"

    titleIdstring = par(str(ratingdict[reviewId]["titleId"]))
    if (len(titleIdstring) == 0):
        titleIdstring = " "
    


    writestring += "(\'" + reviewIdstring + "\'" + "," + "\'" + customerIdstring + "\'" + ","
    writestring += "\'" + ratingstring + "\'" + ","
    writestring += "\'" + datestring + "\'" + "," + "\'" + titleIdstring
    writestring += "\'" + ")" 

    if (reviewId != lastkey):
        writestring += ","
    
    nameWrite.write(writestring)
nameWrite.write(";")






nameWrite.close()
nameRead.close()
