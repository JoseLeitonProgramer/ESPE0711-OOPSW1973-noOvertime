const readlineSync = require('readline-sync');
const Chicken = require('./Chicken');
const FileManager = require('./FileManager');

let chickens = FileManager.read();
let chicken;

function mainMenu() {
  let option;

  do {
    console.log("\n---- Menu ----");
    console.log("1.- Enter chicken");
    console.log("2.- Read chicken");
    console.log("3.- Update chicken");
    console.log("4.- Delete chicken");
    console.log("5.- Quit");
    option = readlineSync.questionInt("Enter the option: ");

    switch (option) {
      case 1:
        chicken = Chicken.enterChicken();
        chickens.push(chicken);
        FileManager.save(chickens);
        console.log(`Chicken added: ${chicken}`);
        break;
      case 2:
        console.log("Listing all chickens:");
        Chicken.readChicken(chickens);
        break;
      case 3:
        console.log("Updating chicken...");
        const idToUpdate = readlineSync.questionInt("Enter ID of a chicken to update: ");
        chicken = Chicken.findChickenById(chickens, idToUpdate);
        if (chicken) {
          Chicken.updateChicken(chicken);
          FileManager.save(chickens);
        } else {
          console.log(`Chicken with ID ${idToUpdate} not found.`);
        }
        break;
      case 4:
        console.log("Deleting chicken...");
        const idToDelete = readlineSync.questionInt("Enter ID of a chicken to delete: ");
        Chicken.deleteChicken(chickens, idToDelete);
        FileManager.save(chickens);
        break;
      case 5:
        FileManager.save(chickens);
        console.log("Saving data and exiting...");
        break;
      default:
        console.log("Invalid option. Please try again.");
    }
  } while (option !== 5);
}

mainMenu();
