//------------------------------------------------------------------------------
// <auto-generated>
//     Codice generato da un modello.
//
//     Le modifiche manuali a questo file potrebbero causare un comportamento imprevisto dell'applicazione.
//     Se il codice viene rigenerato, le modifiche manuali al file verranno sovrascritte.
// </auto-generated>
//------------------------------------------------------------------------------

namespace PCarpet
{
    using System;
    using System.Collections.Generic;
    
    public partial class book
    {
        public int id { get; set; }
        public string username { get; set; }
        public int id_slot { get; set; }
        public int id_payment { get; set; }
    
        public virtual payment payment { get; set; }
        public virtual slot slot { get; set; }
        public virtual user user { get; set; }
    }
}
