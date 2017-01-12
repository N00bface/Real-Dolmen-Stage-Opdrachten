/*
 * Copyright (c) 2017. MIT-license for Jari Van Melckebeke
 * Note that there was a lot of educational work in this project,
 * this project was (or is) used for an assignment from Realdolmen in Belgium.
 * Please just don't abuse my work
 */

// Validation errors messages for Parsley
// Load this after Parsley

Parsley.addMessages('cs', {
  dateiso:  "Tato položka musí být datum ve formátu RRRR-MM-DD.",
  minwords: "Tato položka musí mít délku nejméně %s slov.",
  maxwords: "Tato položka musí mít délku nejvíce %s slov.",
  words:    "Tato položka musí být od %s do %s slov dlouhá.",
  gt:       "Tato hodnota musí být větší.",
  gte:      "Tato hodnota musí být větší nebo rovna.",
  lt:       "Tato hodnota musí být menší.",
  lte:      "Tato hodnota musí být menší nebo rovna."
});
