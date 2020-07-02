package lt.practice.realestate;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.common.collect.Lists;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.Mockito.doReturn;
import static org.mockito.ArgumentMatchers.any;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;



import lt.practice.realestate.models.Building;
import lt.practice.realestate.models.PropertyType;
import lt.practice.realestate.service.BuildingsService;

@SpringBootTest
@AutoConfigureMockMvc
public class AppControllerTest {
	
	@MockBean
	private BuildingsService service;
	
	@Autowired
	private MockMvc mockMvc;
	
    @Test
    @DisplayName("GET /api/buildings OK")
    public void testFindAll() throws Exception {

    	Building b1 = new Building("Vilniaus g. 2, Vilnius", "Company", 78.98, 230000, PropertyType.HOUSE);
    	Building b2 = new Building("Kauno g. 10-3, Kaunas", "Jonas", 108.98, 330000, PropertyType.APARTMENT);       
    	doReturn(Lists.newArrayList(b1, b2)).when(service).getAllBuildings();
    	
    	this.mockMvc.perform(get("/api/buildings"))
    	.andExpect(status().isOk())
    	.andExpect(content().contentType(MediaType.APPLICATION_JSON))
    	.andExpect(jsonPath("$[0].address").value("Vilniaus g. 2, Vilnius"))
    	.andExpect(jsonPath("$[0].owner").value("Company"))
    	.andExpect(jsonPath("$[0].size").value(78.98))
    	.andExpect(jsonPath("$[0].marketValue").value(230000))
    	.andExpect(jsonPath("$[0].propertyType").value("HOUSE"))
    	.andExpect(jsonPath("$[1].address").value("Kauno g. 10-3, Kaunas"))
    	.andExpect(jsonPath("$[1].owner").value("Jonas"))
    	.andExpect(jsonPath("$[1].size").value(108.98))
    	.andExpect(jsonPath("$[1].marketValue").value(330000))
    	.andExpect(jsonPath("$[1].propertyType").value("APARTMENT"));
	}
	
	@Test
	@DisplayName("POST /api/buildings CREATED")
	public void testCreateBuilding() throws Exception {
		
		 Building buildingPost = new Building("Vilniaus g. 2, Vilnius", "Company", 78.98, 230000, PropertyType.HOUSE);
	     Building buildingReturn = new Building(1L, "Vilniaus g. 2, Vilnius", "Company", 78.98, 230000, PropertyType.HOUSE); 
	     doReturn(buildingReturn).when(service).createBuilding(any()); 
	     
	     this.mockMvc.perform(post("/api/buildings")
	    	.contentType(MediaType.APPLICATION_JSON)
	    	.content(asJsonString(buildingPost)))
			.andExpect(status().isCreated())
			.andExpect(content().contentType(MediaType.APPLICATION_JSON))
	     	.andExpect(jsonPath("$.address").value("Vilniaus g. 2, Vilnius"))
	    	.andExpect(jsonPath("$.owner").value("Company"))
	    	.andExpect(jsonPath("$.size").value(78.98))
	    	.andExpect(jsonPath("$.marketValue").value(230000))
	    	.andExpect(jsonPath("$.propertyType").value("HOUSE"));
	}
	
	
	@Test
	@DisplayName("GET /api/buildings/{owner} OK")
	public void testGetBuildingsByOwner() throws Exception {
		Building building = new Building("Vilniaus g. 2, Vilnius", "Company", 78.98, 230000, PropertyType.HOUSE);
		doReturn(Lists.newArrayList(building)).when(service).getBuildingsByOwner("Company");
		
		this.mockMvc.perform(get("/api/buildings/{owner}", "Company"))
		.andExpect(status().isOk())
		.andExpect(content().contentType(MediaType.APPLICATION_JSON))
		.andExpect(jsonPath("$[0].address").value("Vilniaus g. 2, Vilnius"))
    	.andExpect(jsonPath("$[0].owner").value("Company"))
    	.andExpect(jsonPath("$[0].size").value(78.98))
    	.andExpect(jsonPath("$[0].marketValue").value(230000))
    	.andExpect(jsonPath("$[0].propertyType").value("HOUSE"));
	}
	
	@Test
	@DisplayName("GET /api/buildings/tax/{owner}")
	public void testGetTaxByOwner() throws Exception {
		Building b1 = new Building("Vilniaus g. 1, Vilnius", "Company", 78.98, 230000, PropertyType.APARTMENT);
		Building b2 = new Building(1L, "Vilniaus g. 2, Vilnius", "Company", 78.98, 230000, PropertyType.HOUSE);
		doReturn(Lists.newArrayList(b1, b2)).when(service).getAllBuildings();
		doReturn(13800.00).when(service).getTaxByOwner("Company");
		
		this.mockMvc.perform(get("/api/buildings/tax/{owner}", "Company"))
		.andExpect(status().isOk());
		
	}
   
	

	static String asJsonString(final Object obj) {
        try {
            return new ObjectMapper().writeValueAsString(obj);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
