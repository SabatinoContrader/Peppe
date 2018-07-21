using PCarpet.DTO;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Web;

namespace PCarpet.Service
{
    public class MasterService
    {
        public MasterService()
        {
        }


        public void updateSlaves(List<SlaveDTO> slaveDTOs)
        {
            using (pcarpetEntities context = new pcarpetEntities())
            {
                foreach(SlaveDTO slaveDTO in slaveDTOs)
                {
                    slave slave = context.slave.FirstOrDefault(s => s.id.Equals(slaveDTO.id));
                    slave.state = slaveDTO.state;
                    context.Entry(slave).State = System.Data.Entity.EntityState.Modified;
                    context.SaveChanges();
                }
            }

        }

        public List<master> getMasters(int id_slot)
        {
            List<master> masters;
            using (pcarpetEntities context = new pcarpetEntities())
            {
                masters = context.master.Where(m => m.id_slot.Equals(id_slot)).ToList();
            }
            return masters;

        }


        //ADD METHODS

        //returns the added slot
        public SlotDTO addSlot(SlotDTO slotDTO)
        {
            SlotDTO addedSlot;
            using (pcarpetEntities context = new pcarpetEntities())
            {
                slot slot = context.slot.Add(new slot(slotDTO));
                context.SaveChanges();
                addedSlot = slot.toSlotDTO(slot);
            }
            return addedSlot;
        }

        //returns the added master
        public MasterDTO addMaster(MasterDTO masterDTO)
        {
            MasterDTO addedMaster;
            using (pcarpetEntities context = new pcarpetEntities())
            {
                master master = context.master.Add(new master(masterDTO));
                context.SaveChanges();
                addedMaster = master.toMasterDTO(master);
            }
            return addedMaster;
        }

        //returns the added slave
        public SlaveDTO addSlave(SlaveDTO slaveDTO)
        {
            SlaveDTO addedSlave;
            using (pcarpetEntities context = new pcarpetEntities())
            {
                slave slave = context.slave.Add(new slave(slaveDTO));
                context.SaveChanges();
                addedSlave = slave.toSlaveDTO(slave);
            }
            return addedSlave;
        }

        //DELETE METHODS

        //delete slots in slot service

        //returns the id of deleted master
        public string deleteMaster(string id)
        {
            deleteAllSlaves(id);

            string idDeleted;
            using (pcarpetEntities context = new pcarpetEntities())
            {
                master master = getMaster(id);
                context.master.Attach(master);
                master deletedMaster = context.master.Remove(master);
                context.SaveChanges();
                idDeleted = deletedMaster.id;
            }

            return idDeleted;
        }

        //returns the id of deleted slave
        public string deleteSlave(string id)
        {
            string idDeleted;
            using (pcarpetEntities context = new pcarpetEntities())
            {
                slave slave = getSlave(id);
                context.slave.Attach(slave);
                slave deletedSlave = context.slave.Remove(slave);
                context.SaveChanges();
                idDeleted = deletedSlave.id;
            }

            return idDeleted;
        }


        public void deleteAllMasters(int id_slot)
        {
            using (pcarpetEntities context = new pcarpetEntities())
            {
                context.master.RemoveRange(context.master.Where(m => m.id_slot == id_slot));
                context.SaveChanges();
            }
        }

        public void deleteAllSlaves(string id_master)
        {
            using (pcarpetEntities context = new pcarpetEntities())
            {
                context.slave.RemoveRange(context.slave.Where(s => s.id_master == id_master));
                context.SaveChanges();
            }
        }

        //UPDATE METHODS

        //returns the updated slot
        public SlotDTO updateSlot(SlotDTO slotDTO)
        {
            SlotDTO updatedSlot;
            using (pcarpetEntities context = new pcarpetEntities())
            {
                slot slot = context.slot.FirstOrDefault(s => s.id.Equals(slotDTO.id));
                slot.latitude = slotDTO.latitude;
                slot.longitude = slotDTO.longitude;
                slot.price = slotDTO.price;
                slot.address = slotDTO.address;
                slot.type = slotDTO.type;
                context.Entry(slot).State = System.Data.Entity.EntityState.Modified;
                context.SaveChanges();
                updatedSlot = slot.toSlotDTO(slot);
            }
            return updatedSlot;
        }

        //returns the updated master
        public MasterDTO updateMaster(MasterDTO masterDTO)
        {
            MasterDTO updatedMaster;
            using (pcarpetEntities context = new pcarpetEntities())
            {
                master master = context.master.FirstOrDefault(m => m.id.Equals(masterDTO.id));
                //cambierà il tipo quando ci sarà
                //master.type = masterDTO.type;

                context.Entry(master).State = System.Data.Entity.EntityState.Modified;
                context.SaveChanges();
                updatedMaster = master.toMasterDTO(master);
            }
            return updatedMaster;
        }

        //returns the updated slave]
        public SlaveDTO updateSlave(SlaveDTO slaveDTO)
        {
            SlaveDTO updatedSlave;
            using (pcarpetEntities context = new pcarpetEntities())
            {
                slave slave = context.slave.FirstOrDefault(s => s.id.Equals(slaveDTO.id));
                //cambierà se ci sarà qualcosa da cambiare
                //slave.type = slave.type;

                context.Entry(slave).State = System.Data.Entity.EntityState.Modified;
                context.SaveChanges();
                updatedSlave = slave.toSlaveDTO(slave);
            }
            return updatedSlave;
        }

        //GET METHODS

        public List<MasterDTO> getMastersDTO(int id_slot)
        {
            using (pcarpetEntities context = new pcarpetEntities())
            {
                List<master> masters = context.master.Where(m => m.id_slot.Equals(id_slot)).ToList();
                List<MasterDTO> masterDTO = new List<MasterDTO>();
                foreach (master master in masters)
                {
                    masterDTO.Add(master.toMasterDTO(master));
                }
                return masterDTO;
            }         
        }

        public List<SlaveDTO> getSlaves(string id_master)
        {
            using (pcarpetEntities context = new pcarpetEntities())
            {
                List<slave> slaves = context.slave.Where(s => s.id_master.Equals(id_master)).ToList();
                List<SlaveDTO> slaveDTO = new List<SlaveDTO>();
                foreach (slave slave in slaves)
                {
                    slaveDTO.Add(slave.toSlaveDTO(slave));
                }
                return slaveDTO;
            }
        }

        //metodi di supporto
        public master getMaster(string id_master)
        {
            using (pcarpetEntities context = new pcarpetEntities())
            {
                return context.master.FirstOrDefault(m => m.id.Equals(id_master));
            }
        }

        public slave getSlave(string id_slave)
        {
            using (pcarpetEntities context = new pcarpetEntities())
            {
                return context.slave.FirstOrDefault(s => s.id.Equals(id_slave));
            }
        }

    }
}