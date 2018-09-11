This a Poker game validator that will let any user know which hand win a play
 
 ## How to calculate the game weight
 
 to calculate the game weight we need to follow the minimum value to UP 
 
 the minimum values in a Poker game is the High Card then we can say
 High Card has two values to consider 
 -  minimum which is 2
 -  maximum which is 14(A representation)
 Considering it we need to know how calculate the next in the table which will be Pairs
 Pairs has also two values to consider 
 -  minimum which is 4
 -  maximum which is 28(double A representation)
 
 but some high cards can have bigger value than Pairs then we need assign a weight to game itself
 
 which value should be assigned?
 well we are taking a value that make the minimum greater than previous maximum, that means
 
 if High Card has 14 maximum Pairs minimum should be greater than it, 
 then pairs should be (minimum*gameweight)=> 4x5=20
 
 to follow this logic we are also calculating the hand weight with nly the relevant cards,
 that means from the High Card we will take only the highest and in the pair only the pair and will assign this to the hand wight
 
 