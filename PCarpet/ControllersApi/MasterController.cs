using PCarpet.DTO;
using PCarpet.Service;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Net;
using System.Net.Http;
using System.Web.Http;
using System.Web.Http.Cors;

namespace PCarpet.ControllersApi
{
    [EnableCors(origins: "*", headers: "*", methods: "*")]
    [RoutePrefix("api")]
    public class MasterController : ApiController
    {
        private MasterService masterService;
        private SlotService slotService;

        public MasterController()
        {
            this.masterService = new MasterService();
            this.slotService = new SlotService();
        }

        [HttpPost]
        [Route("updateSlaves")]
        public void updateSlaves(List<SlaveDTO> slaveDTOs)
        {
            this.masterService.updateSlaves(slaveDTOs);
        }


        //ADD METHODS

        //returns the added slot
        [HttpPost]
        [Route("addSlot")]
        public SlotDTO addSlot(SlotDTO slotDTO)
        {
            return this.masterService.addSlot(slotDTO);
        }

        //returns the added master
        [HttpPost]
        [Route("addMaster")]
        public MasterDTO addMaster(MasterDTO masterDTO)
        {
            return this.masterService.addMaster(masterDTO);
        }

        //returns the added slave
        [HttpPost]
        [Route("addSlave")]
        public SlaveDTO addSlave(SlaveDTO slaveDTO)
        {
            return this.masterService.addSlave(slaveDTO);
        }

        //DELETE METHODS

        //returns the id of deleted slot
        [HttpDelete]
        [Route("deleteSlot")]
        public int deleteSlot(int id)
        {
            return this.slotService.deleteSlot(id);
        }

        //returns the id of deleted master
        [HttpDelete]
        [Route("deleteMaster")]
        public string deleteMaster(string id)
        {
            return this.masterService.deleteMaster(id);
        }

        //returns the id of deleted slave
        [HttpDelete]
        [Route("deleteSlave")]
        public string deleteSlave(string id)
        {
            return this.masterService.deleteSlave(id);
        }

        //UPDATE METHODS

        //returns the updated slot
        [HttpPut]
        [Route("updateSlot")]
        public SlotDTO updateSlot(SlotDTO slotDTO)
        {
            return this.masterService.updateSlot(slotDTO);
        }

        //returns the updated master
        [HttpPut]
        [Route("updateMaster")]
        public MasterDTO updateMaster(MasterDTO masterDTO)
        {
            return this.masterService.updateMaster(masterDTO);
        }

        //returns the updated slave
        [HttpPut]
        [Route("updateSlave")]
        public SlaveDTO updateSlave(SlaveDTO slaveDTO)
        {
            return this.masterService.updateSlave(slaveDTO);
        }

        //GET METHODS

        [HttpGet]
        [Route("getSlots")]
        public List<SlotDTO> getSlots(string username)
        {
            return this.slotService.getSlots(username);
        }

        [HttpGet]
        [Route("getMasters")]
        public List<MasterDTO> getMastersDTO(int id_slot)
        {
            return this.masterService.getMastersDTO(id_slot);
        }

        [HttpGet]
        [Route("getSlaves")]
        public List<SlaveDTO> getSlaves(string id_master)
        {
            return this.masterService.getSlaves(id_master);
        }

    }
}
