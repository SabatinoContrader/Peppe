using PCarpet.DTO;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Web;

namespace PCarpet.Service
{
    public class CarService
    {

        public void addCar(CarDTO carDTO)
        {
            using (pcarpetEntities context = new pcarpetEntities())
            {
                context.car.Add(new car(carDTO));
                context.SaveChanges();
            }
                
        }


        public void removeCar(int id)
        {
            using (pcarpetEntities context = new pcarpetEntities())
            {
                car car = getCar(id);
                context.car.Attach(car);
                context.car.Remove(car);
                context.SaveChanges();
            }
        }


        public List<CarDTO> getAllCarDTO(string username)
        {
            using (pcarpetEntities context = new pcarpetEntities())
            {
                List<car> cars = context.car.Where(car => car.username.Equals(username)).ToList();
                List<CarDTO> carsDTO = new List<CarDTO>();
                foreach (car car in cars)
                {
                    carsDTO.Add( car.toCarDTO(car) );
                }
                return carsDTO;
            }              
        }



        //metodi interni
        public car getCar(int id_car)
        {
            using (pcarpetEntities context = new pcarpetEntities())
            {
                return context.car.FirstOrDefault(c => c.id.Equals(id_car));
            }
        }

        public List<car> getAllCar(string username)
        {
            using (pcarpetEntities context = new pcarpetEntities())
            {
                return context.car.Where(car => car.username.Equals(username)).ToList();
            }
        }
    }
}