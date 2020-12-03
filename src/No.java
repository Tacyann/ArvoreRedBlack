public class No {
   
    private Integer valor;
	
	private No left;
	private No rigth;
	private No pai;
	
	private int cor;//1 para vermelho e -1 para preto
		
	public No() {
		this(null,null,null,null,1);
	}
	
	public No(Integer valor,No rigth,No left,No pai,int cor) {
		this.valor = valor;
		this.rigth = rigth;
		this.left = left;
		this.pai = pai;
		this.cor = cor;
		
	}
	
	public Integer getValor() {
		return valor;
	}
	
	public void setValor(Integer valor) {
		this.valor = valor;
	}
	
	public No getLeft() {
		return left;
	}
	
	public void setLeft(No left) {
		this.left = left;
	}
	
	public No getRigth() {
		return rigth;
	}
	
	public void setRigth(No rigth) {
		this.rigth = rigth;
	}
	
	public No getPai() {
		return pai;
	}
	
	public void setPai(No pai) {
		this.pai = pai;
	}
	
	public void setNo(Integer valor,No rigth,No left,No pai,int cor) {
		this.valor = valor;
		this.rigth = rigth;
		this.left = left;
		this.pai = pai;
		this.cor = -1;
	}
	
	public void setCor(int cor) {
		this.cor = cor;
	}
	
	public int getCor() {
		return cor;
	}
}
