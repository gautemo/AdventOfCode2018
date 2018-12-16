using System;
using System.Linq;
using System.Collections.Generic;
using System.Diagnostics;

namespace day14
{
    class HotChocolateCreating
    {
        static void Main(string[] args)
        {
            //var res = StartMakingRecipes("37", 380621); //Task 1
            var res = StartMakingRecipesUntilMatch("37", "380621"); //Task 2
            Console.WriteLine(res);
        }

        public static string StartMakingRecipes(string startRecipes, int improvedAfter)
        {
            var elves = new int[]{0,1};
            var recipes = startRecipes.ToList();
            while(recipes.Count < improvedAfter + 10){
                AddRecipes(elves, recipes);
            }
            return Get10Next(recipes, improvedAfter);
        }

        public static int StartMakingRecipesUntilMatch(string startRecipes, string lookFor)
        {
            var elves = new int[]{0,1};
            var recipes = startRecipes.ToList();
            var res = -1;
            while(res == -1){
                AddRecipes(elves, recipes);
                res = RecipesBeforeScoreSequence(recipes, lookFor);
            }
            return res;
        }

        static void AddRecipes(int[] elves, List<char> recipes)
        {
            var one = Convert.ToInt32(Char.GetNumericValue(recipes.ElementAt(elves[0])));
            var two = Convert.ToInt32(Char.GetNumericValue(recipes.ElementAt(elves[1])));
            var newRecipe = (one + two).ToString().ToList();
            newRecipe.ForEach(s => recipes.Add(s));
            elves[0] = (elves[0] + one + 1) % recipes.Count;
            elves[1] = (elves[1] + two + 1) % recipes.Count;
        }

        public static string Get10Next(List<char> recipes, int beginAfter)
        {
            return String.Join("", recipes.GetRange(beginAfter, 10));
        }

        public static int RecipesBeforeScoreSequence(List<char> recipes, string lookFor)
        {
            if(recipes.Count < lookFor.Length + 1) return -1;
            if(recipes[recipes.Count-1] != lookFor[lookFor.Length-1] && recipes[recipes.Count-2] != lookFor[lookFor.Length-1]) return -1;
            if(String.Join("", recipes.GetRange(recipes.Count - (lookFor.Length + 1), lookFor.Length + 1)).Contains(lookFor)){
                return String.Join("", recipes).IndexOf(lookFor);
            }
            return -1;
        }
    }
}
