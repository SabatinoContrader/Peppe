using PCarpet.DTO;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Web;

namespace PCarpet
{
    public partial class car
    {
        public car(CarDTO carDTO)
        {
            this.id = carDTO.id;
            this.license_plate = carDTO.license_plate;
            this.name = carDTO.name;
            this.username = carDTO.username;
        }

        public static CarDTO toCarDTO(car car)
        {
            return new CarDTO(car.id, car.name, car.username);
        }
    }
}