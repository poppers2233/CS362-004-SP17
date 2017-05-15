#include "dominion.h"
#include "dominion_helpers.h"
#include <string.h>
#include <assert.h>
#include <stdio.h>
#include "rngs.h"
#include <stdlib.h>


int main(int argc, char *argv[]){
	
	printf("Unit test 1:  intializeGame");
	
	int players = atoi(argv[1]);	
	struct gameState G;
	
	int k[10] = {adventurer, council_room, feast, gardens, mine,
	       remodel, smithy, village, baron, great_hall};
	int check;	
	check = initializeGame(players,k,1,&G);

	printf("#players in : %i\n",players);
	printf("#Kingdom cards in: 10\n");
	
	int results[18];
	printf("0 = numPlayers\n");
	printf("1 = supplyCount ->Size\n");
	printf("2 = EmbargoTokens ->Size\n");
	printf("3 = outPostPlayed\n");
	printf("4 = outPostTurn\n");
	printf("5 = whoseTurn\n");
	printf("6 = phase\n");
	printf("7 = numActions\n");
	printf("8 = coins\n");
	printf("9 = hand->size\n");
	printf("10 = handCount->size\n");
	printf("11 = deck->size\n");
	printf("12 = deckCount->size\n");
	printf("13 = discard->size\n");
	printf("14 = discardCount->size\n");
	printf("15 = numBuys\n");
	printf("16 = playedCard->size\n");
	printf("17 = playedCcardCount\n");

	results[0] = G.numPlayers;
	results[1] = sizeof(G.supplyCount)/4;	
	results[2] = sizeof(G.embargoTokens)/4;
	results[3] = G.outpostPlayed;
	results[4] = G.outpostTurn;
	results[5] = G.whoseTurn;
	results[6] = G.phase;
	results[7] = G.numActions;
	results[8] = G.coins;
	results[9] = sizeof(G.hand[0])/4;
	results[10] = sizeof(G.handCount)/4;
	results[11] = sizeof(G.deck[0])/4;
	results[12] = sizeof(G.deckCount)/4;
	results[13] = sizeof(G.discard[0])/4;
	results[14] = sizeof(G.discardCount)/4;
	results[15] = G.numBuys;
	results[16] = sizeof(G.playedCards)/4;
	results[17] = G.playedCardCount;

	if(check != 0){
		int i=0;
		
		for(i=0;i<18; i++){
			printf("%i : %i ",i,results[i]);
			printf("----->\n");
		}
		return -1;
	}
	else{
		printf("ALL TEST PASS\n");
	}
	
	printf("\n\n\n");
	return 0;
}
