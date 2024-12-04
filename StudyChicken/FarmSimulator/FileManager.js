const fs = require('fs');
const Chicken = require('./Chicken');

const FileManager = {
  DEFAULT_FILE_PATH: 'chickens.json',

  save: function (chickens, fileName = this.DEFAULT_FILE_PATH) {
    try {
      fs.writeFileSync(fileName, JSON.stringify(chickens, null, 2));
      console.log(`Data saved in ${fileName}`);
    } catch (error) {
      console.log(`Error saving data: ${error.message}`);
    }
  },

  read: function (fileName = this.DEFAULT_FILE_PATH) {
    let chickens = [];
    if (!fs.existsSync(fileName)) {
      console.log('File does not exist. Creating new file with an empty list.');
      this.save(chickens, fileName);
      return chickens;
    }

    try {
      const data = fs.readFileSync(fileName, 'utf8');
      if (!data.trim()) {
        console.log('File is empty. Starting with an empty list.');
        return chickens;
      }

      const chickenArray = JSON.parse(data);
      chickens = chickenArray.map(chicken => new Chicken(chicken.id, chicken.name, chicken.color, chicken.ageInMonths, chicken.bornOnDate, chicken.notMolting));
      console.log(`Successfully loaded ${chickens.length} chickens from ${fileName}`);
    } catch (error) {
      console.error(`Error reading file: ${error.message}`);
    }

    return chickens;
  }
};

module.exports = FileManager;
