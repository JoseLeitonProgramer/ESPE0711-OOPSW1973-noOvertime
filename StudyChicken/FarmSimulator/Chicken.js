const fs = require('fs');

class Chicken {
  constructor(id, name, color, ageInMonths, bornOnDate, notMolting) {
    this.id = id;
    this.name = name;
    this.color = color;
    this.ageInMonths = ageInMonths || 0;
    this.bornOnDate = bornOnDate || '';
    this.notMolting = notMolting || false;
    if (bornOnDate) {
      this.ageInMonths = this.computeAgeInMonths();
    }
  }

  static enterChicken() {
    const readlineSync = require('readline-sync');
    const id = readlineSync.questionInt("Enter id of chicken: ");
    const name = readlineSync.question("Enter name of chicken: ");
    const color = readlineSync.question("Enter color of chicken: ");
    const bornOnDate = readlineSync.question("Enter birth date of chicken (YYYY-MM-DD): ");
    const notMolting = readlineSync.keyInYNStrict("Is molting? (y/n): ");

    return new Chicken(id, name, color, 0, bornOnDate, !notMolting);
  }

  static readChicken(chickens) {
    if (chickens.length === 0) {
      console.log("No chickens available.");
      return;
    }

    chickens.forEach(chicken => console.log(chicken.toString()));
  }

  static findChickenById(chickens, id) {
    return chickens.find(chicken => chicken.id === id);
  }

  static updateChicken(chicken) {
    const readlineSync = require('readline-sync');
    console.log("Select the attribute to update:");
    console.log("1. Name");
    console.log("2. Color");
    console.log("3. Not Molting");
    const choice = readlineSync.questionInt("Enter your choice: ");

    switch (choice) {
      case 1:
        chicken.name = readlineSync.question("Enter new name: ");
        console.log(`Name updated to: ${chicken.name}`);
        break;
      case 2:
        chicken.color = readlineSync.question("Enter new color: ");
        console.log(`Color updated to: ${chicken.color}`);
        break;
      case 3:
        chicken.notMolting = readlineSync.keyInYNStrict("Is molting? (y/n): ");
        console.log(`Molting status updated to: ${chicken.notMolting}`);
        break;
      default:
        console.log("Invalid choice.");
    }
  }

  static deleteChicken(chickens, id) {
    const chickenToDelete = Chicken.findChickenById(chickens, id);
    if (chickenToDelete) {
      const index = chickens.indexOf(chickenToDelete);
      chickens.splice(index, 1);
      console.log(`Chicken with ID ${id} has been deleted.`);
    } else {
      console.log(`Chicken with ID ${id} not found.`);
    }
  }

  computeAgeInMonths() {
    const birthDate = new Date(this.bornOnDate);
    const currentDate = new Date();
    const diffInTime = currentDate - birthDate;
    const diffInMonths = diffInTime / (1000 * 3600 * 24 * 30);
    return Math.floor(diffInMonths);
  }

  toString() {
    return `Chicken{id=${this.id}, name=${this.name}, color=${this.color}, age=${this.ageInMonths}, BornOnDate=${this.bornOnDate}, notMolting=${this.notMolting}}`;
  }
}

module.exports = Chicken;
