import sys
inf=sys.argv[1]

with open(inf) as fin, open(inf+".newnames") as fout:
	line=fin.readline()
	while line:
		cols=line.split("\t",2)
		newname=cpls[1]
		newname=newname.replace(",","_").replace(" ","_").replace(":","_")
		cols[0]=newname
		cols[1]=newname
		fout.write("\t".join(cols))
		line=fin.readline()
