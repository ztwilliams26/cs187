public class LLDogNode {
	
  private Dog info;
  private LLDogNode link;
   
  public LLDogNode (Dog dog, LLDogNode link) {
    this.info = dog; 
    this.link = link;
  }
   
  public Dog getInfo() {
    return info;
  }
   
  public LLDogNode getLink() {
    return link;
  }
   
  public void setInfo(Dog dog) {
    info = dog;
  }
   
  public void setLink (LLDogNode link) {
    this.link = link;
  }
}
