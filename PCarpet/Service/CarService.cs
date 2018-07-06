using System;
using System.Collections.Generic;
using System.Linq;
using System.Web;

namespace PCarpet.Service
{
    public class CarService
    {

        public void addCar(car car)
        {
            using (pcarpetEntities context = new pcarpetEntities())
            {
                context.car.Add(car);
                context.SaveChanges();
            }
                
        }


        public void removeCar(car car)
        {
            using (pcarpetEntities context = new pcarpetEntities())
            {
                context.car.Attach(car);
                context.car.Remove(car);
                context.SaveChanges();
            }
        }


        public List<car> getAllCar(user user)
        {
            using (pcarpetEntities context = new pcarpetEntities())
            {
                return context.car.Where(car => car.username.Equals(user.username)).ToList();
            }
                
        }

        public car getCar(int id_car)
        {
            using (pcarpetEntities context = new pcarpetEntities())
            {
                return context.car.FirstOrDefault(c => c.id.Equals(id_car));
            }
        }

    }
}