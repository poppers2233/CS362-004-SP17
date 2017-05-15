
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
	
	printf("Random num checks = %i\n",RnumChecks);
	struct gameState states[RnumChecks];
	int allPass = 1;

        int count=0;
        int count2=0;

        printf("adventurer unit random  test ----\n");
        int k[10] = {adventurer, council_room, feast, gardens, mine,
               remodel, smithy, village, baron, great_hall};
	
        int i;
	int a;	
	for(a=0; a<RnumChecks; a++){
		count = 0;
		count2 = 0;
		r = initializeGame(2, k, 1, &states[a]);
		
        for(i=0; i< sizeof(states[a].hand[0])/4;i++){
                if(states[a].hand[0][i] == copper){
                        count++;

                }

        }
        check = cardEffect(adventurer,0,0,0,&states[a],0,0);

        for(i=0; i< sizeof(states[a].hand[0])/4;i++){
                if(states[a].hand[0][i] == copper){
                        count2++;
                }

        }

        if(count+2 == count2){

                printf("PASS\n");

        }

        else{
                printf("FAILED\n");
		allPass = 0;
        }
		printf("count  = %i\n",count);
		printf("count2 = %i\n",count2);
		
	}
	
	if(allPass == 1)
        	return 0;
	
	if(allPass == 0)
		return -1;
	
	return -1;
}
