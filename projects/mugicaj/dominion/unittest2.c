#include "dominion.h"
#include "dominion_helpers.h"
#include <string.h>
#include <stdio.h>
#include <assert.h>
#include "rngs.h"


int main(){
	int r;
	int check;
	printf("numHandCards unit test ----\n");
  	int k[10] = {adventurer, council_room, feast, gardens, mine,
	       remodel, smithy, village, baron, great_hall};

	struct gameState G;
  
 	 r = initializeGame(4, k, 1, &G);

	check = numHandCards(&G);
	
	if(check == 5){
		printf("PASS\n");
		return 0;
	}
	
	else{
		printf("FAIL\n");
		return -1;
	
	}
	
	return 0;
}
