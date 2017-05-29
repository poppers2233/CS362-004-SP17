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
        int count=0;
	
	int RnumChecks = (rand() % 20) + 1;
	struct gameState states[RnumChecks];

	printf("Random num checks = %i\n",RnumChecks);	
	
	int allPass = 1;
        printf("council_room random test ----\n");
        int k[10] = {adventurer, council_room, feast, gardens, mine,
               remodel, smithy, village, baron, great_hall};

        struct gameState G;

        int i;
	int a;	
	for(a=0; a<RnumChecks; a++){
		count=0;
		r = initializeGame(2, k, 1, &states[a]);

	
	check = cardEffect(council_room,0,0,0,&states[a],0,0);
        for(i=0;i<sizeof(states[a].hand[0])/4;i++){
                if(states[a].hand[0][i]>0){
                        count++;
                }

        }
        if(count == 8 && states[a].numBuys == 2){

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
