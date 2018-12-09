using System;
using System.Collections.Generic;

namespace day8
{
    class Program
    {
        static void Main(string[] args)
        {
            ReadFile(out string[] lines);
            var root = new Node();
            Init(lines[0], root);
            Console.WriteLine(root.MetadataSum());
            Console.WriteLine(root.MetadataSumIndexes());
        }

        private static void ReadFile(out string[] lines){
            lines = System.IO.File.ReadAllLines(@"input.txt");
        }

        public static string Init(string info, Node n){
            var data = new List<string>(info.Split(' '));
            var nrChilds = Convert.ToInt32(data[0]);
            var nrMetadata = Convert.ToInt32(data[1]);
            data.RemoveRange(0,2);
            for(int i=0; i<nrChilds; i++){
                var c = new Node();
                data = new List<string>(Init(String.Join(" ", data), c).Split(' '));
                n.Nodes.Add(c);
            }
            for(int i=0; i<nrMetadata; i++){
                n.MetadataEntries.Add(Convert.ToInt32(data[0]));
                data.RemoveAt(0);
            }
            return String.Join(" ", data);
        }
    }
}
