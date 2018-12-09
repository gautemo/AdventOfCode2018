using System;
using System.Collections.Generic;
using System.Linq;

namespace day8
{
    class Node
    {
        public List<int> MetadataEntries { get; set; } = new List<int>();
        public List<Node> Nodes { get; set; } = new List<Node>();

        public int MetadataSum(){
            int entries = MetadataEntries.Sum();
            foreach(var n in Nodes){
                entries += n.MetadataSum();
            }
            return entries;
        }

        public int MetadataSumIndexes(){
            if(Nodes.Count == 0) return MetadataSum();
            int sum = 0;
            foreach(var i in MetadataEntries){
                if(i > 0 && i <= Nodes.Count){
                    sum += Nodes[i-1].MetadataSumIndexes();
                }
            }
            return sum;
        }
    }
}