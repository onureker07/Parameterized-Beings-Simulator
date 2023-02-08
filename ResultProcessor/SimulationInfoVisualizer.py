import matplotlib.pyplot as plt

def readData(fileName:str):
    
    file = open(fileName, "r")
    
    line = file.readline()
    
    line = line.split(", ")
    
    index = []
    instance = []
    prey = []
    carnivorous = []
    preyProduction = []
    carnivorousProduction = []
    preyFood = []
    carnivorousPrey = []
    
    for line in file:
        
        line = line.split(", ")
        
        index.append(int(line[0]))
        instance.append(int(line[1]))
        prey.append(int(line[2]))
        carnivorous.append(int(line[3]))
        preyProduction.append(float(line[4]))
        preyFood.append(float(line[5]))
        carnivorousProduction.append(float(line[6]))
        carnivorousPrey.append(float(line[7]))
    
    file.close()
    
    return index, instance, prey, carnivorous, preyProduction, carnivorousProduction, preyFood, carnivorousPrey

def plotData(index, instance, prey, carnivorous, preyProduction, carnivorousProduction, preyFood, carnivorousPrey):
    #Find the last file index from Plots directory
    lastFileIndex = 0
    while True:
        try:
            _ = open("Plots/Instance" + str(lastFileIndex) + ".png", "r")
            lastFileIndex = lastFileIndex + 1
        except:
            break
    #Index vs. Instance
    plt.plot(index, instance)
    plt.xlabel("Time Step")
    plt.ylabel("Instance")
    plt.title("Timestep vs. Instance")
    #Save the plot as a png file
    plt.savefig("Plots/Instance" + str(lastFileIndex) + ".png")
    plt.show()

    #Index vs. Prey and Carnivorous
    plt.plot(index, prey, label = "Prey")
    plt.plot(index, carnivorous, label = "Carnivorous")
    plt.xlabel("Time Step")
    plt.ylabel("Population")
    plt.title("Time Step vs. Population")
    plt.legend()
    #Save the plot as a png file
    plt.savefig("Plots/Population" + str(lastFileIndex) + ".png")
    plt.show()

    #Index vs. Prey Production and Carnivorous Production
    plt.plot(index, preyProduction, label = "Prey Production")
    plt.plot(index, carnivorousProduction, label = "Carnivorous Production")
    plt.xlabel("Time Step")
    plt.ylabel("Production")
    plt.title("Time Step vs. Production")
    plt.legend()
    #Save the plot as a png file
    plt.savefig("Plots/Production" + str(lastFileIndex) + ".png")
    plt.show()

    #Index vs. Prey Food and Carnivorous Prey
    plt.plot(index, preyFood, label = "Average Food eaten by Prey")
    plt.plot(index, carnivorousPrey, label = "Average Food eaten by Carnivorous")
    plt.xlabel("Time Step")
    plt.ylabel("Average Eaten Nutrition")
    plt.title("Time Step vs. Food")
    plt.legend()
    #Save the plot as a png file
    plt.savefig("Plots/Food" + str(lastFileIndex) + ".png")
    plt.show()
    

if __name__ == "__main__":
    index, instance, prey, carnivorous, preyProduction, carnivorousProduction, preyFood, carnivorousPrey = readData("LotkaVolterraData.txt")
    plotData(index, instance, prey, carnivorous, preyProduction, carnivorousProduction, preyFood, carnivorousPrey)



