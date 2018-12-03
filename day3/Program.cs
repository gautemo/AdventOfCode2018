using System;
using System.Collections.Generic;

namespace day3
{
    class Program
    {
        static void Main(string[] args)
        {
            var map = new Dictionary<string, int>();
            ReadFile(out string[] lines);
            ClaimFabric(map, lines);
            int multipleClaims = 0;
            foreach(var pair in map){
                if(pair.Value > 1)
                {
                    multipleClaims++;
                }
            }
            Console.WriteLine("Overlapping inches: " + multipleClaims);
            foreach(string line in lines){
                if(hasNoOverlap(map, line)){
                    Console.WriteLine("No overlap: " + line.Split(' ')[0].Replace(":#", ""));
                }
            }
        }

        private static void ReadFile(out string[] lines){
            lines = System.IO.File.ReadAllLines(@"input.txt");
        }

        private static void ClaimFabric(Dictionary<string, int> map, string[] lines){
            foreach(string line in lines){
                getClaimNumbers(line, out int x, out int y, out int w, out int h);
                for(int i = 0; i<w; i++)
                {
                    for(int j = 0; j<h; j++)
                    {
                        string place = $"{x + i},{y + j}";
                        if(map.TryGetValue(place, out int value))
                        {
                            map[place]++;
                        }else
                        {
                            map.Add(place, 1);
                        }
                    }
                }
            }
        }

        private static bool hasNoOverlap(Dictionary<string, int> map, string line)
        {
            getClaimNumbers(line, out int x, out int y, out int w, out int h);
            for(int i = 0; i<w; i++)
            {
                for(int j = 0; j<h; j++)
                {
                    string place = $"{x + i},{y + j}";
                    if(map.TryGetValue(place, out int value))
                    {
                        if(value != 1){
                            return false;
                        }   
                    }
                }
            }
            return true;
        }

        private static void getClaimNumbers(string line, out int x, out int y, out int w, out int h)
        {
            var info = line.Split(' ');
            x = Convert.ToInt32(info[2].Split(',')[0]);
            y = Convert.ToInt32(info[2].Split(',')[1].Replace(":", ""));
            w = Convert.ToInt32(info[3].Split('x')[0]);
            h = Convert.ToInt32(info[3].Split('x')[1]);
        }
    }
}
