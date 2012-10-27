package testing;

import Models.Address;
import Models.Coach;
import Models.Official;
import Models.Player;
import Models.Team;
import Models.User;

//Jack Young
public class TestDataLoader {

	/******Create test users *****/

	//regular user
	public User getUser1() {
		User u = new User();
		u.setFirstName("Alexia");
		u.setLastName("Lucas");
		u.setPhone("234 223 4023");
		u.setAddress(createAddress1());
		return u;
	}
	
	//player user 1
	public User getUser2() {
		Player p = new Player();
		p.setCaptain(false);
		p.setFirstName("Jack");
		p.setLastName("Young");
		p.setPhone("703 9680 264");
		p.setAddress(createAddress2());
		return p;
	}
	
	//player user 2
	public Player getUser3() {
		Player p = new Player();
		p.setAddress(createAddress3());
		p.setCaptain(true);
		p.setFirstName("Duc");
		p.setLastName("Truong");
		p.setPhone("213 455 2323");
		
		
		return p;
	}
	
	//coach user
	public Coach getUser4() {
		Coach c = new Coach();
		c.setAddress(createAddress4());
		c.setFirstName("Thomas");
		c.setLastName("Young");
		c.setPhone("213 788 4020");
		c.getTeam().setName("Thunder Bolts");
		c.getTeam().setAwayColor("Red");
		c.getTeam().setHomeColor("Blue");
		return c;
	}
	
	// offical user
	public Official getUser5() {
		Official o = new Official();
		o.setAddress(createAddress5());
		o.setFirstName("Brian");
		o.setLastName("Vamieer");
		o.setPhone("563 234 5566");
		return o;
	}
	
	//coach user
	public Coach getUser6() {
		Coach c = new Coach();
		c.setAddress(createAddress6());
		c.setFirstName("Marcus");
		c.setLastName("Cook");
		c.setPhone("413 788 4020");
		c.getTeam().setName("DC United");
		c.getTeam().setAwayColor("Black");
		c.getTeam().setHomeColor("White");
		return c;
	}
	
	
	public Team getTestTeam() {
		Team t = new Team();
		t.setAwayColor("Black");
		t.setHomeColor("Blue");
		t.setName("DC United");
		return t;
	}
	
	public Team getTestTeam2() {
		Team t = new Team();
		t.setAwayColor("Green");
		t.setHomeColor("Yellow");
		t.setName("ThunderBolts");
		return t;
	}
	
	
	//Create the addresses used for testing
	public Address createAddress1() {
		Address a = new Address();
		a.setStreet("9502 Ironmaster Drive");
		a.setState("VA");
		a.setZip("22015");
		a.setCity("Burke");
		return a;
	}
	
	public Address createAddress2() {
		Address a = new Address();
		a.setStreet("5209 Rosalie Ridge Drive");
		a.setState("VA");
		a.setZip("20120");
		a.setCity("Centreville");
		return a;
	}
	
	public Address createAddress3() {
		Address a = new Address();
		a.setStreet("1400 University Drive");
		a.setState("VA");
		a.setZip("22030");
		a.setCity("Fairfax");
		return a;
	}
	
	public Address createAddress4() {
		Address a = new Address();
		a.setStreet("42566 Stires Drive");
		a.setState("VA");
		a.setZip("20152");
		a.setCity("South Riding");
		return a;
	}
	
	public Address createAddress5() {
		Address a = new Address();
		a.setStreet("4219 Stepney Dr");
		a.setState("VA");
		a.setZip("20152");
		a.setCity("Gainesville");
		return a;
	}
	
	public Address createAddress6() {
		Address a = new Address();
		a.setStreet("4212 Happy Moore Crt");
		a.setState("VA");
		a.setZip("2021");
		a.setCity("Chantilly");
		return a;
	}
}
