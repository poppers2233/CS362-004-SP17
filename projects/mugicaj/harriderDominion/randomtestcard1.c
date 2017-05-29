#include "dominion.h"
#include "dominion_helpers.h"
#include <string.h>
#include <stdio.h>
#include <assert.h>
#include "rngs.h"
#include <stdlib.h>
#include <time.h>


int main(){

        srand(time(NULL));


        int r;
        int check;

        int RnumChecks = (rand() % 20) + 1;
        int count=0;
	struct gameState states[RnumChecks];
	printf("Random num checks = %i\n",RnumChecks);

        int allPass = 1;
        printf("smithy random test ----\n");
        int k[10] = {adventurer, council_room, feast, gardens, mine,
               remodel, smithy, village, baron, great_hall};

        struct gameState G;

        //check = cardEffect(smithy,0,0,0,&G,0,0);
         //one card discarded 3 added

        int i;
	int a;
        for(a=0; a<RnumChecks; a++){
		count=0;
	r = initializeGame(2, k, 1, &states[a]);
	check = cardEffect(smithy,0,0,0,&states[a],0,0);
        for(i=0;i<10; i++){
                //printf("%i\n",states[a].hand[0][i]);
                if(states[a].hand[0][i] > 0){
                        count++;
                }

        }
        if(count == 7){

                printf("PASS\n");

        }

        else{
                printf("FAILED\n");
                allPass = 0;
     	}
        }
        if(allPass == 1)
                return 0;

        if(allPass == 0)
                return -1;
	
	return -1;
}

