#include "dominion.h"
#include "dominion_helpers.h"
#include <string.h>
#include <stdio.h>
#include <assert.h>
#include "rngs.h"



int main(){

	int r;
        int check;
	int count=0;
        printf("smithy unit test ----\n");
        int k[10] = {adventurer, council_room, feast, gardens, mine,
               remodel, smithy, village, baron, great_hall};

        struct gameState G;

         r = initializeGame(2, k, 1, &G);
	
	check = playSmithy(&G,0,0);
	// one card discarded 3 added
	int i;
	for(i=0;i<10; i++){
		printf("%i\n",G.hand[0][i]);
		if(G.hand[0][i] > 0){
			count++;
		}
	
	}
	if(count == 7){
	
		printf("PASS\n");
		return 0;
		
	}
	
 	else{
		printf("FAILED\n");
		return -1;
	}
	
	return 0;
	 
}
