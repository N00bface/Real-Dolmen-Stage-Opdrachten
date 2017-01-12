/*
 * Copyright (c) 2017. MIT-license for Jari Van Melckebeke
 * Note that there was a lot of educational work in this project,
 * this project was (or is) used for an assignment from Realdolmen in Belgium.
 * Please just don't abuse my work
 */

// Validation errors messages for Parsley
import Parsley from '../parsley';

Parsley.addMessages('ro', {
  dateiso:    "Trebuie să fie o dată corectă (YYYY-MM-DD).",
  minwords:   "Textul e prea scurt. Trebuie să aibă cel puțin %s cuvinte.",
  maxwords:   "Textul e prea lung. Trebuie să aibă cel mult %s cuvinte.",
  words:      "Textul trebuie să aibă cel puțin %s și cel mult %s caractere.",
  gt:         "Valoarea ar trebui să fie mai mare.",
  gte:        "Valoarea ar trebui să fie mai mare sau egală.",
  lt:         "Valoarea ar trebui să fie mai mică.",
  lte:        "Valoarea ar trebui să fie mai mică sau egală.",
  notequalto: "Valoarea ar trebui să fie diferită."
});
