using Xunit;
using System.Linq;
using System.Collections.Generic;

namespace day14
{
    public class HotChocolateCreatingTest
    {
        [Fact]
        public void Get10NextTest()
        {
            Assert.Equal("5158916779", HotChocolateCreating.Get10Next("37101012451589167792".ToList(), 9));
        }

        [Fact]
        public void StartMakingRecipesTest()
        {
            Assert.Equal("5158916779", HotChocolateCreating.StartMakingRecipes("37", 9));
            Assert.Equal("0124515891", HotChocolateCreating.StartMakingRecipes("37", 5));
            Assert.Equal("9251071085", HotChocolateCreating.StartMakingRecipes("37", 18));
            Assert.Equal("5941429882", HotChocolateCreating.StartMakingRecipes("37", 2018));
        }

        [Fact]
        public void RecipesBeforeScoreSequenceTest()
        {
            Assert.Equal(9, HotChocolateCreating.RecipesBeforeScoreSequence("37101012451589".ToList(), "51589"));
            Assert.Equal(5, HotChocolateCreating.RecipesBeforeScoreSequence("37101012451".ToList(), "01245"));
        }

        [Fact]
        public void StartMakingRecipesUntilMatchTest()
        {
            Assert.Equal(9, HotChocolateCreating.StartMakingRecipesUntilMatch("37", "51589"));
            Assert.Equal(5, HotChocolateCreating.StartMakingRecipesUntilMatch("37", "01245"));
            Assert.Equal(18, HotChocolateCreating.StartMakingRecipesUntilMatch("37", "92510"));
            Assert.Equal(2018, HotChocolateCreating.StartMakingRecipesUntilMatch("37", "59414"));
        }
    }
}