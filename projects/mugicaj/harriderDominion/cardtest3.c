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
        printf("council_room unit test ----\n");
        int k[10] = {adventurer, council_room, feast, gardens, mine,
               remodel, smithy, village, baron, great_hall};

        struct gameState G;

         r = initializeGame(2, k, 1, &G);

        check = playCouncilRoom(&G,0,0);
	int i;
	for(i=0;i<sizeof(G.hand[0])/4;i++){
		if(G.hand[0][i]>0){
			count++;
		}
	
	}
        if(count == 8 && G.numBuys == 2){

                printf("PASS\n");
                return 0;

        }

        else{
                printf("FAILED\n");
		printf("count = %i\n",count);
		printf("G.numbuys = %i\n",G.numBuys);
                return -1;
        }

        return 0;

}
