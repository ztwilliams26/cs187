package testPlayground;

public class nullPrimitive<T> {
	public T element;
	public nullPrimitive(T element){
		this.element=element;
	}
	public nullPrimitive() {
		
	}
	public T returnElement() {
		if(element==null) {
			return null;
		}
		return element;
	}
	public static void main(String[] args) {
		// TODO Auto-generated method stub
	}

}
