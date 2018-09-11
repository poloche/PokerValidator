This a Poker game validator that will let any user know which hand win a play
 
 to know the winner we need to take into account 2 weights 
 - hand weight
 - game weight  
 
 ## what is the hand weight?
 The hand weight will be the summation of the relevant cards, by example in a High Card 
 the relevant one is the Highest one and the maximum will he the A representation which is 14
 
 For Pairs we need to sum the pairs value which minimum will be 4 and maximum 28
 For Three we will take the sum of the 3 cards which als will have minimum and maximum
 And we will follow all the poker rules to calculate the hand weight  
 
 ## what is the game weight?
 This is a value that make any hand with specific game(poker, full, three, etc) heavier than
 the previous in the responsibility chain.  
 
 ## How to calculate the game weight
 
 to calculate the game weight we need to follow the minimum value to UP 
 
 the minimum values in a Poker game is the High Card then we can say
 High Card has two values to consider 
 -  minimum which is 2
 -  maximum which is 14(A representation)
 
 Considering it we need to know how to calculate the next in the table which will be Pairs
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
 
 ## how to recognize which game has any hand?
 we are applying chain responsibility pattern to recognize each hand
 
 The starting point will be the RoyalFlush and the last one will be the HighCard 