#Read Results/simulationInfo5.txt
import SimulationInfoVisualizer
import numpy as np

#Read Results/simulationInfo5.txt with readData()
index, instance, prey, carnivorous, preyProduction, carnivorousProduction, preyFood, carnivorousPrey = SimulationInfoVisualizer.readData("Results/simulationInfo5.txt")





# change in prey population = alpha * prey population - beta * prey population * carnivorous population
# change in carnivorous population = gamma * prey population * carnivorous population - delta * carnivorous population
# learn the parameters alpha, beta, gamma, delta

#Create a new data for the prey population change every time step
preyChange = []
for i in range(len(prey) - 1):
    preyChange.append(prey[i + 1] - prey[i])

#Create a new data for the carnivorous population change every time step
carnivorousChange = []
for i in range(len(carnivorous) - 1):
    carnivorousChange.append(carnivorous[i + 1] - carnivorous[i])

#Create a new data for prey population * carnivorous population
preyCarnivorous = []
for i in range(len(prey)):
    preyCarnivorous.append(prey[i] * carnivorous[i])

#concatenate the prey population  and preyCarinivorous
train_1 = np.concatenate((np.array(prey[:-1]).reshape(-1, 1), np.array(preyCarnivorous[:-1]).reshape(-1, 1)), axis = 1)

#import RandomForestRegressor
from sklearn.linear_model import LinearRegression

#Learn the parameters alpha, beta, gamma, delta
lr = LinearRegression(fit_intercept=False)
lr.fit(train_1, preyChange)
alpha = lr.coef_[0]
beta = -1*lr.coef_[1]
print("Alpha: ", alpha)
print("Beta: ", beta)

#concatenate the carnivorous population and preyCarinivorous
train_2 = np.concatenate((np.array(carnivorous[:-1]).reshape(-1, 1), np.array(preyCarnivorous[:-1]).reshape(-1, 1)), axis = 1)
lr = LinearRegression(fit_intercept=False)
lr.fit(train_2, carnivorousChange)
delta = -1*lr.coef_[0]
gamma = lr.coef_[1]
print("Gamma: ", gamma)
print("Delta: ", delta)



#Draw Lotka-Volterra model with the learned parameters
import matplotlib.pyplot as plt

initail_prey = 195
initail_carnivorous = 71
prey = [initail_prey]
carnivorous = [initail_carnivorous]
for i in range(85000):
    change_in_prey = alpha * prey[i] - beta * prey[i] * carnivorous[i]
    change_in_carnivorous = gamma * prey[i] * carnivorous[i] - delta * carnivorous[i]
    prey.append(prey[i] + change_in_prey)
    carnivorous.append(carnivorous[i] + change_in_carnivorous)

plt.plot(prey, label = "Prey")
plt.plot(carnivorous, label = "Carnivorous")
plt.legend()
#save the figure
plt.savefig("Plots/LotkaVolterra.png")
plt.show()


