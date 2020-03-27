public class DogTeam {

  private LLDogNode head;

  public DogTeam(Dog dog) {
    head = new LLDogNode(dog, null);
  }

  public void printTeam() {
    LLDogNode cur = head;
    int dogNumber = 1;
    
    System.out.println("----------------");
    while (cur != null) {
      System.out.println(dogNumber + ". " + cur.getInfo().getName() +
                         ", " + cur.getInfo().getWeight());
      cur = cur.getLink();
      dogNumber += 1;
    }
  }


  public static void main(String[] args) {        
    
    DogTeam team = new DogTeam(new Dog("dog1", 60));        
    team.printTeam();
    System.out.println("weightDiff: " + team.weightDiff());
    
    team.insertTail(new Dog("dog0",  5));
    team.insertHead(new Dog("dog2",  90));
    team.printTeam();
    System.out.println("weightDiff: " + team.weightDiff());
    
    team.insertHead(new Dog("dog3",  7));
    team.insertAfter(new Dog("dog4",  100), "dog2");
    team.printTeam();
    
    team.insertTail(new Dog("dog10", 205));        
    team.insertAfter(new Dog("dog9", 75), "dog10");
    
    team.printTeam();
    System.out.println("weightDiff: " + team.weightDiff());
    
  }

  public void insertHead(Dog dog) {
    // TODO(1)
    // create and insert a new dog node at the head of the list
      LLDogNode temp= new LLDogNode(dog, head);
      head=temp;
 
    
  }

  public void insertTail(Dog dog) {
    // TODO(2)
    // create and insert a new dog node at the tail of the list
      LLDogNode temp= new LLDogNode(dog, null);
      LLDogNode node= head;
      while(node.getLink() != null){
          node = node.getLink();
      }
      node.setLink(temp);
      
  }

  public double weightDiff() {
    // TODO(3)
    // return the difference between the max and min weights of dogs
    // in the list. You can safely assume that when this method is called,
    // the list contains at least one node
      double weightDifference=0;
      double  maxWeight=head.getInfo().getWeight();
      double  minWeight=head.getInfo().getWeight();
      LLDogNode node=head;
      while(node!=null){
          if(node.getInfo().getWeight()>maxWeight){
              maxWeight=node.getInfo().getWeight();
          }
          if(node.getInfo().getWeight()<minWeight){
              minWeight=node.getInfo().getWeight();
              
          }
          node=node.getLink();
      }
      weightDifference=maxWeight-minWeight;
      if(weightDifference!=0) return weightDifference;
      return 0.0;

  }
  
  public void insertAfter(Dog dog, String dogName) {
      // TODO(4)
    // find the node containing a dog named dogName,
    // then create and insert a new node after that node.
      LLDogNode temp=new LLDogNode(dog,null);
      LLDogNode node=head;
      LLDogNode beforeDog;
      LLDogNode afterDog;
      while(node.getLink() != null){
          if(node.getInfo().getName().equals(dogName)){
              beforeDog=node;
              afterDog=node.getLink();
              beforeDog.setLink(temp);
              temp.setLink(afterDog);
              
          }
          node=node.getLink();
      }
      
  }
}