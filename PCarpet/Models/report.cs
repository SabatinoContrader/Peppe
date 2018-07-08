using System;
using System.Collections.Generic;
using System.Linq;
using System.Web;

namespace PCarpet
{
    public partial class report
    {
        public report() { }

        public report(int id, int type, string description, byte[] time, string username, int state)
        {
            this.id = id;
            this.type = type;
            this.description = description;
            //this.time = time;
            this.username = username;
            this.state = state;
        }
    }
}