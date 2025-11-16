import random

def main():
    Width, Height, numberOfSpotlights, theExactRoundsOfSpotlights, numberOfWorms, board, worms = read_input()
    total_beats = theExactRoundsOfSpotlights[-1]

    for beat in range(total_beats):
        if beat in theExactRoundsOfSpotlights:
            print("x")
        else:
            worm_index, direction = chooseRandomMove(numberOfWorms)
            print(worm_index, direction)

def chooseRandomMove(numberOfWorms):
    ''' Choose a random worm and a random direction to turn '''
    left = 'l'
    right = 'r'

    worm_index = random.randint(0, numberOfWorms-1)
    randomDirection = random.choice([left, right])
    return worm_index, randomDirection

def collision(worms):
    ''' Check if any worms overlap '''

    for i in range(len(worms)):
        for j in range(i+1, len(worms)):
            for segmentA in worms[i]:
                if segmentA in worms[j]:
                    return True
        
    return False

def scorePoint(board, worms):
    ''' Score points based on worm positions and board colors '''
    score = 0
    for y in range(Height):
        for x in range(Width):
            color = board[y][x]
            for worm in worms:
                for index, (sx, sy) in enumerate(worm):
                    if sx == x and sy == y:
                        if (index < 3 and color == 'B') or (index >= 3 and color == 'R'):
                            score += 1

    return score


def read_input():
    ''' Read and validate input data '''

    Width, Height = input('Type width and height etc: ').split()
    Width, Height = int(Width), int(Height)

    if not (10 <= Width <= 25 and 10 <= Height <= 25):
        print("Width and Height must be between 10 and 25")
        return None

    dataOfSpotLightInfo = input('Type number of spotlights and rounds etc: ')
    splitData = dataOfSpotLightInfo.split()

    numberArray = []
    for part in splitData:
        numberArray.append(int(part))
    
    if len(numberArray) > 3:
        print("Invalid spotlight data")
        return None

    numberOfSpotlights = numberArray[0]
    theExactRoundsOfSpotlights = numberArray[1:]

    numberOfWorms = input('Type number of worms: ')
    numberOfWorms = int(numberOfWorms)

    if numberOfWorms < 3 or numberOfWorms > 10:
        print("There must be at least 3 worms and at most 10 worms")
        return None

    board = []
    for i in range(Height):
        row = input('Type row: R B etc x amount must match height ')

        if(len(row.split()) != Width):
            print("Row length does not match width")
            return None
        
        board.append(row.split())
    

    worms = []
    for i in range(numberOfWorms):
        wormData = input('Type worm data: 1,2 ; (x,y) etc ')

        if len(wormData.split()) != 6:
            print("Each worm must have exactly 6 segments (coordinates that you input)")
            return None
        
        parts = wormData.split()
        worm = []
        for part in parts:
            x, y = part.split(",")
            x, y = int(x), int(y)
            worm.append((x, y))
        worms.append(worm)

    return Width, Height, numberOfSpotlights, theExactRoundsOfSpotlights, numberOfWorms, board, worms

if __name__ == "__main__":
    main()