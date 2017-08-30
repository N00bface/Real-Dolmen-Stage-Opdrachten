/*
 * Copyright (c) 2017. MIT-license for Jari Van Melckebeke
 * Note that there was a lot of educational work in this project,
 * this project was (or is) used for an assignment from Realdolmen in Belgium.
 * Please just don't abuse my work
 */

// Validation errors messages for Parsley
// Load this after Parsley

Parsley.addMessages('el', {
  dateiso:  "Η τιμή πρέπει να είναι μια έγκυρη ημερομηνία (YYYY-MM-DD).",
  minwords: "Το κείμενο είναι πολύ μικρό. Πρέπει να έχει %s ή και περισσότερες λέξεις.",
  maxwords: "Το κείμενο είναι πολύ μεγάλο. Πρέπει να έχει %s ή και λιγότερες λέξεις.",
  words:    "Το μήκος του κειμένου είναι μη έγκυρο. Πρέπει να είναι μεταξύ %s και %s λεξεων.",
  gt:       "Η τιμή πρέπει να είναι μεγαλύτερη.",
  gte:      "Η τιμή πρέπει να είναι μεγαλύτερη ή ίση.",
  lt:       "Η τιμή πρέπει να είναι μικρότερη.",
  lte:      "Η τιμή πρέπει να είναι μικρότερη ή ίση.",
  notequalto: "Η τιμή πρέπει να είναι διαφορετική."
});
