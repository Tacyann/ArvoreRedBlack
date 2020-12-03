public class RubroNegra {
    
    private No root;
	private int tamanho;
	
	public RubroNegra() {
		root = new No();
		tamanho = 0;
	}
	
	private boolean isExternal(No novo) {
		if(novo != null && (novo.getLeft() == null && novo.getRigth() == null)) {
			return true;
		}
		return false;
	}
	
	private boolean isEmpty() {
		if(tamanho == 0) {
			return true;
		}
		return false;
	}
	
	//metodo para inserir nos na arvore
	public void inserirNo(Integer valor) {
		
		if(isEmpty()) {//se a arvore esta vazia
			root = new No(valor,null,null,null,-1);
			tamanho++;
		}else {//se a arvore possui Nos
			inseriNoRecursivo(root,valor);
			tamanho++;
		}
			
	}
	
	//cahadas recursivar para achar  lugar do novo no inserido
	private void inseriNoRecursivo(No aux,Integer valor) {
		No novo;
		
		if(valor < aux.getValor()) {//se valor for menor que o atual
			//vai pra esquerda
			
			if(aux.getLeft() == null) {//se 
				novo = new No(valor,null,null,aux,1);
				aux.setLeft(novo);
				//avl(novo);//chama metodo de balanceamento avl
				rubroNegraInserir(novo);//chama metodo de balanceamento passando o no inserido
				
			}else {
				inseriNoRecursivo(aux.getLeft(),valor);
			}
		}
		
		if(valor > aux.getValor()) {
			//vai pra direita
			
			if(aux.getRigth() == null) {
				novo = new No(valor,null,null,aux,1);
				aux.setRigth(novo);
				//avl(novo);chama metodo de desbalanceamento
				rubroNegraInserir(novo);//chama metodo de balanceamento passando o no inserido

			}else {
				inseriNoRecursivo(aux.getRigth(),valor);
			}
		}
		
	}//fim do metodo inserirNoRecursivo
	
	//metodo para excluir Nos
	public void excluirNo(Integer valor) {
		
		No base = pesquisarNoIterativo(valor);//o No a ser excluidp a partir do valor passado
		
		if(base != null) {
			rubroNegraExcluir(base);//faz o balanceamento antes de excluir

			if(isExternal(base)) {  //é uma folha
				No aux = base.getPai();//nó substituto

				if(base == root) {//se o nó base é  raiz
					tamanho--;
					root = new No();
					
				}else {//se o nó base não é a raiz
					if(filhoEsq(aux,base)) { //base é filho da esquerda
					
						base.setPai(null);//base deixa de apontar para o pai
						aux.setLeft(null);//pai da base deixa de apontar para ela
						//avl(aux);//chama funco de balanceamento
					
					}else {//base é o filho da direita
					
						base.setPai(null);//base deixa de apontar para o pai
						aux.setRigth(null);//pai da base deixa de apontar para ela
						//avl(aux);//chama funcao de balanceamento
					
					}
				}
				
			}else if((base.getLeft() == null) || (base.getRigth() == null)) {//se tem só um filho
				//System.out.println("teste2");
				No aux;
				
				if(base == root){//se a base é a raiz
					if(base.getLeft() != null) {//se é o filho a esquerda da base
						aux = base.getLeft();
						
						aux.setPai(null);//muda para null o pai do filho esquerda da base
						base.setLeft(null);	//muda para null o filho a esquerda da base
						root = aux;//raiz recebe o aux
						//avl(root);
						
					}else if(base.getRigth() != null) {//se é o filho a direita da base
						aux = base.getRigth();
							
						aux.setPai(null);//muda para null o pai do filho a direita
						base.setRigth(null);//muda para null o filho a direita da base
						root = aux;//raiz recebe aux
						//avl(aux);//chama funcao de balanceamento
					
					}
				}else {
					if(filhoEsq(base.getPai(),base)) {//se a base é o filho a esquerda
						aux = base.getPai();
					
						if(base.getLeft() != null) {//se é o filho a esquerda da base
					
							base.getLeft().setPai(aux);//filho da base aponta para o pai da base
							aux.setLeft(base.getLeft());//pai da base aponta para filho a esquerda da base
					
							base.setPai(null);
							base.setLeft(null);	
							//avl(aux);
						
						}else if(base.getRigth() != null) {//se é o filho a direita da base
						
							base.getRigth().setPai(aux);//filho da bae aponta para o pai da base
							aux.setLeft(base.getRigth());//pai da base aponta para filho a direita da base
						
							base.setPai(null);
							base.setRigth(null);
							//avl(aux);//chama funcao de balanceamento
					
						}
					
					}else{//se a base é o filho a direita
						aux = base.getPai();
					
						if(base.getLeft() != null) {//se é o filho a esquerda da base
					
							base.getLeft().setPai(aux);//filho da base aponta para o pai da base
							aux.setRigth(base.getLeft());//pai da base aponta para filho a esquerda da base
					
							base.setPai(null);
							base.setLeft(null);	
							//avl(aux);
						
						}else if(base.getRigth() != null) {//se é o filho a direita da base
						
							base.getRigth().setPai(aux);//filho da bae aponta para o pai da base
							aux.setRigth(base.getRigth());//pai da base aponta para filho a direita da base
						
							base.setPai(null);
							base.setRigth(null);
							//avl(aux);//chama funcao de balanceamento
					
						}
					}
				}
			}else if(base.getLeft() != null && base.getRigth() != null) {//se base tem dois filhos
				No aux;
				
				if(base == root) {//se o nó excluido for a raiz
					aux = proximoSucessor(base.getRigth());//decide qual nó subistituira o nó excluído
					
					if(aux == base.getRigth()) {//se aux é o filho a direita da base
						aux.setLeft(base.getLeft());//muda o filho da esquerda de aux para o filho a esquerda da base
						base.getLeft().setPai(aux);//muda o pai do filho a esquerda da base para aux
						
						base.setLeft(null);//
						base.setRigth(null);
						base.setPai(null);
						
						root = aux;
						root.setPai(null);
						
					}else {//se aux é outro nó qualquer
						aux.getPai().setLeft(null);//muda filho a esquerda do pai de aux pra null
						aux.setLeft(base.getLeft());
						aux.setRigth(base.getRigth());
						
						base.getLeft().setPai(aux);
						base.getRigth().setPai(aux);
						
						base.setPai(null);
						base.setLeft(null);
						base.setRigth(null);
						
						root = aux;
						root.setPai(null);
						
					}	
				}else {//se o nó excluido não for a raiz
					aux = proximoSucessor(base.getRigth());
					
					if(aux == base.getRigth()) {//se aux é o filho a direita da base
						aux.setLeft(base.getLeft());//muda o filho da esquerda de aux para o filho a esquerda da base
						aux.setPai(base.getPai());//muda pai do aux para pai da base
						
						
						base.getLeft().setPai(aux);//muda o pai do filho a esquerda da base para aux
						
						if(filhoEsq(base.getPai(),base)) {//se a base é o filho a esquerda
							base.getPai().setLeft(aux);//muda filho a esquerdaa do pai da base para aux
							
						}else {
							base.getPai().setRigth(aux);
						}
						base.setLeft(null);//
						base.setRigth(null);
						base.setPai(null);
						
					}else {//se aux é outro nó qualquer
						aux.getPai().setLeft(null);
						aux.setLeft(base.getLeft());//muda flho a esqerda de aux para filho a esquerda da base
						aux.setRigth(base.getRigth());//muda filho a direita de aux para filho a direita da base
						aux.setPai(base.getPai());//muda pai do aux para pai da base
						
						base.getLeft().setPai(aux);//muda pai do filho a esquerda da base para aux
						base.getRigth().setPai(aux);//muda pai do filho a direita da base para aux
						base.getPai().setRigth(aux);//muda filho a direita do pai da base para aux
						
						base.setPai(null);
						base.setLeft(null);
						base.setRigth(null);
						
					}
				}
			}
			tamanho--;
		}
	}//fim do metodo excluirNo
	
	//retorna true caso o no passdo seja o filho a esquerda
	public boolean filhoEsq(No pai,No filho) {
		
		if((pai.getValor() > filho.getValor()) &&(filho != null)) {
			return true;
		}
		return false;
	}//im do metodo filhoEsq
	
	//recebe como parametro inicial o No filho a direita
	private No proximoSucessor(No base) {
		
		if(base.getLeft() != null) {
			return proximoSucessor(base.getLeft());
			
		}else {
			return base;
		}
		
	}//fim do metodoProximoSucessor
	
	//recebe o No filho a esquerda como parametro inicial
	private No proximoAntecessor(No base) {
		
		if(base.getRigth() != null) {
			return proximoAntecessor(base.getRigth());
			
		}else {
			return base;
		}
	}//fim do metodo proximoAntecesor
	
	//pesquisa iterativa de nos
	public No pesquisarNoIterativo(Integer valor) {
		
		No aux = root;//aux recebe a raiz
		
		if(isEmpty()) {//se a arvore esta vazia
			return null;
			
		}else {//se tem elementos na arvore
			
			while(aux.getValor() != null) {//enquanto o valor de aux for diferente de nulo
				
				if(valor < aux.getValor()) {//se o valor parrado for menor que o valor de aux
					aux = aux.getLeft();//aux recebe o filho a esquerda
					
				}else if(valor > aux.getValor()) {//se o valor for maior
					aux = aux.getRigth();//aux recebe o filho a direita
					
				}else {//qualquer outro resultado
					break;//sai do while
				}
			}
			return aux;//retorna o No aux
		}
	}//fim do metodo pesquisaNoIterativo
	
	//retorna valores em caminho prefixado
	public void prefixado(No base) {
		
		if(base != null) {
			System.out.printf("\t(%d cor = %d)",base.getValor(),verificarCor(base));
			prefixado(base.getLeft());
			prefixado(base.getRigth());
		}
	}//fim do metodo prefixado
	
	//retorna valores em caminho pos fixado
	public void posfixado(No base) {
		if(base != null) {
			posfixado(base.getLeft());
			posfixado(base.getRigth());
			System.out.printf("\t%d",base.getValor());
		}
	}//fim do metodo posfixado
	
	//retorna valores em ordem crescente
	public void interfixado(No base) {
		
		if(base != null) {
			
			interfixado(base.getLeft());
			System.out.printf("\t%d",base.getValor());
			interfixado(base.getRigth());	
			
		}
	}//fim do metodo interfixado
	
	//calcula altura de um no
	public Integer altura(No base) {
		
		if(base != null) {
			
			if(isExternal(base)) {
				return 0;
				
			}else {
				return 1 + (Math.max(altura(base.getLeft()),altura(base.getRigth())));
			}
		}
		return -1;  
		
	}//fim do metodo altura
	
	//calcular a profundidade recursivamente
	public Integer profundidade(No base) {
		
		if(base != null) {
			
			if(base == root) {
				return 0;
				
			}else {
				return 1 + (profundidade(base.getPai()));
			}
		}
		return 0;
	}//fim do metodo profundidade
	
	//retorna a raiz
	public No getRoot() {
		return root;
	}
	
	//retorna os valores em ordem decrescente
	public void decrescente(No base) {
		
		if(base != null) {
			decrescente(base.getRigth());
			System.out.printf("\t%d",base.getValor());
			decrescente(base.getLeft());
		}
	}//fim do metodo decrecesnte
	
	//metodo que calcula o fator de balanceamento
	private Integer fatBal(No base) {
	
		if(isExternal(base)) {
			return 0;
		}
		return (altura(base.getRigth()) + 1) - (altura(base.getLeft()) + 1);
		
	}//fim do metodo fatBal
	
	//metodo que rotaciona a subarvore para esquerda caso o fator de balanseamento for +2
	private void rotacaoEsq(No base) {
			
		No aux;
			
		if(base == root) {//se base for igual a raiz
				
			aux = base.getRigth();//no aux Ã© igual ao filho a direita da base
			base.setPai(aux);//seta o o pai da base para aux
				
			if(aux.getLeft() != null) {//se aux tem filho a esquerda
					
				No aux2 = aux.getLeft();//aux2 recebe o filho a esquerda de aux
				aux2.setPai(base);//pai de aux2 recebe a base
				base.setRigth(aux2);//
			}else {
				base.setRigth(null);
			}
				
			aux.setLeft(base);
			root = base.getPai();
			root.setPai(null);
				
		}else if(base.getPai() == root) {//se pai da base for igual a raiz
			
			aux = base.getRigth();//no aux Ã© igual ao filho a direita da base
			base.setPai(aux);//seta o o pai da base para aux
				
			if(aux.getLeft() != null) {//se aux tem filho a esquerda
					
				No aux2 = aux.getLeft();//aux2 recebe o filho a esquerda de aux
				aux2.setPai(base);//pai de aux2 recebe a base
				base.setRigth(aux2);//
			}else {
				base.setRigth(null);
			}
				
			aux.setLeft(base);
			root.setRigth(aux);
			aux.setPai(root);
				
		}else if(base.getPai() != root){//qualquer outro caso
			
			aux = base.getPai();
			base.setPai(aux.getPai());
			aux.getPai().setRigth(base);
			
				
			if(base.getLeft() != null) {
					
				No aux2 = base.getLeft();
				aux.setRigth(aux2);
				aux2.setPai(aux);
			}else {
				aux.setRigth(null);
			}
				
			aux.setPai(base);
			base.setLeft(aux);
				
		}
	}//fim do metodo rotacaoEsq
	
	//metodo que rotaciona a subarvore para esquerda caso o fator de balanseamento for -2
	private void rotacaoDir(No base) {
				
		No aux;
			
		if(base == root) {//se base for igual a raiz
					
			aux = base.getLeft();//no aux Ã© igual ao filho a esquerda da base
			base.setPai(aux);//seta o o pai da base para aux
					
			if(aux.getRigth() != null) {//se aux tem filho a direita
						
				No aux2 = aux.getRigth();//aux2 recebe o filho a direita de aux
				aux2.setPai(base);//pai de aux2 recebe a base
				base.setLeft(aux2);//
			}else {
				base.setLeft(null);
			}
			aux.setRigth(base);
			root = base.getPai();
			root.setPai(null);
					
		}else if(base.getPai() == root) {//se pai da base for igual a raiz
					
			aux = base.getLeft();//no aux Ã© igual ao filho a esquerda da base
			base.setPai(aux);//seta o o pai da base para aux
					
			if(aux.getRigth() != null) {//se aux tem filho a direita
						
				No aux2 = aux.getRigth();//aux2 recebe o filho a direita de aux
				aux2.setPai(base);//pai de aux2 recebe a base
				base.setLeft(aux2);//
			}else {
				base.setLeft(null);
			}
					
			aux.setRigth(base);
			root.setLeft(aux);
			aux.setPai(root);
					
		}else {//qualquer outro caso
					
			aux = base.getPai();//No aux é igual ao pai do No base
			aux.getPai().setLeft(base);//filho a esqueda do avô da base recebe base
			base.setPai(aux.getPai());//pai da base recebe avô d base
					
			if(base.getRigth() != null) {//se base tem filho a direita
					
				No aux2 = base.getRigth();//no aux2 recebe filho a direita da base
				aux.setLeft(aux2);//filho a esquerda de aux recebe aux2
				aux2.setPai(aux);//pai de aux2 recebe aux
					
			}else {//se não tem filho a direita
				aux.setLeft(null);
			}
					
			aux.setPai(base);
			base.setRigth(aux);
					
		}
	}//fim do metodo rotacaoEsq
	
	//metodo que faz rotacao direita esquerda
	private void rotacaoDirEsq(No base) {
		
		if(base == root) {//se o No base é a riz
			No aux = base.getRigth();//aux recebe o filho a direita da base
			No aux2 = aux.getLeft();//aux2 recebe filho a esquerda de aux
		
			aux2.setRigth(aux);//filho a direita de aux2 recebe aux
			aux2.setLeft(base);//filho a esquerda de aux2 recebe base
		
			aux.setPai(aux2);//pai de aux recebe aux2
		
			base.setPai(aux2);//pai da base recebe aux2
			base.setRigth(null);//filho da direita de base recebe null
		    base.setLeft(null);
			aux.setLeft(null);//filho a esquerda de aux recebe null
		    aux.setRigth(null);
			root = aux2;//raiz recebe aux2
			root.setPai(null);//pai da raiz recebe null
			
		}else {//qualquer outro valor
			No aux3 = base.getPai();//aux3 recebe pai da bse
			No aux = base.getRigth();//aux recebe filho a direita da base
			No aux2 = aux.getLeft();//aux2 recebe filho a esquerda de aux
		
			aux2.setRigth(aux);//muda o filho a direita de aux2 para aux
			aux2.setLeft(base);//muda o filho a esquerda de aux2 para base
		
			aux.setPai(aux2);//muda o pai de aux para aux2
		
			base.setPai(aux2);//muda o pai da base para aux2
			base.setRigth(null);//muda o filho a deiteita da base para null
		
			aux.setLeft(null);//muda o filho a esquerda de aux para null
			aux2.setPai(aux3);//muda o pai de aux2 para aux3
			
			if(aux3.getValor() < aux2.getValor()) {//se o valor de aux3 é menor que aux2
				aux3.setRigth(aux2);//muda para aux2 o filho da dereita de aux3 
				
			}else {//se o valoré maior
				aux3.setLeft(aux2);//muda para aux2 o filho a esquerda de aux3
			}
		}
	}//fim do metodo rotacaoDirEsq
	
	//metodo que faz a rotacao esquerda direita
	private void rotacaoEsqDir(No base) {
		
		if(base == root) {//se o No base é igual a raiz
			No aux = base.getLeft();//aux recebe filho a esquerda da base
			No aux2 = aux.getRigth();//aux2 recebe filho a direita de aux
		
			aux2.setRigth(base);//muda para base o fiho a direita de aux2
			aux2.setLeft(aux);//muda para aux o filho a esquerda de aux2
		
			aux.setPai(aux2);//muda para aux2 o pai de aux
		
			base.setPai(aux2);//muda para aux2 o pai da base
			base.setLeft(null);//muda para null o filho a esquerda de base
		    base.setRigth(null);
			aux.setRigth(null);//muda para null o filho a direita de aux
		    aux.setLeft(null);
			root = aux2;//raiz recebe aux2
			root.setPai(null);//muda para null o pai da raiz
			
		}else {//se base é qualqer outro No
			No aux3 = base.getPai();//aux3 reccebe pai da base
			No aux = base.getLeft();//aux recebe filhoa esquerda da bse
			No aux2 = aux.getRigth();//aux2 recebe filho a direita de aux
		
			aux2.setRigth(base);//muda para base o filho a direita de aux2
			aux2.setLeft(aux);//muda para aux o filho a esquerda de ux2
		
			aux.setPai(aux2);//muda para aux2 o pai de aux
		
			base.setPai(aux2);//muda para aux2 o pai da base
			base.setLeft(null);//muda para null o filho a esquerda da base
		
			aux.setRigth(null);//muda para null i filho a direita de aux
			aux2.setPai(aux3);//muda para aux3 o pai de aux3
			
			if(aux3.getValor() < aux2.getValor()) {//se o valor de aux3 é menor que o de au2
				aux3.setRigth(aux2);//muda para aux2 o filho a direita de aux3
				
			}else {//se for maior
				aux3.setLeft(aux2);//muda para aux2 o filho a esquerda de ux3
			}
		}
	}//fim do metodo rotacaoEsqDir
	
	//metodo que calcula a avl
	private void avl(No base) {
		
		if(base != null) {//se base não é nula
			
			if(fatBal(base) == 2) {//se fator de balanceamento é 2
				
				if(fatBal(base.getRigth()) < 0) {//se o filho a direita da base tem fator de balanceamento negativo
					//rotacao direita esquerda
					rotacaoDirEsq(base);
					
				}else {//se o filho tem valor de fator de balanceamento positivo
					//rotacao esquerda
					rotacaoEsq(base);
				}
				
			}else if(fatBal(base) == -2) {//se o fator de balanceamento é -2
				
				if(fatBal(base.getLeft()) > 0) {//se o fatbal do filho a esqueda é positivo
					//rotacao direita esquerda
					rotacaoEsqDir(base);
					
				}else {//se o fatbal do filho a qesquerda é negativo
					//rotacao direita
					rotacaoDir(base);
					
				}
				
			}else {//se a fator de balanceamento não é 2 ou - 2
				avl(base.getPai());//chama a função recursivamente passando o pai da base
			}
		}
	}//fim do metodo avl
	
	//metodo de balanceamento rubro negra na inserção
	private void rubroNegraInserir(No base) {
		
		if(base.getPai().getCor() == 1) {//se a cor do nó pai for vermelho
			No aux = base.getPai().getPai();//aux é igual ao avô da base
			
			if(filhoEsq(aux,base.getPai())) {//se o pai da base é o filho a esquerda

				if(verificarCor(aux.getRigth()) == 1) {//se o tio da base tem cor vermelha
					
					base.getPai().setCor(base.getPai().getCor() * (-1));//muda a cor do pai da base
					
					aux.getRigth().setCor(aux.getRigth().getCor() * (-1));//muda a cor do tio da base
					
					if(aux != root) {//se o avô não for a raiz
						aux.setCor(aux.getCor() * (-1));//muda a cor do avô
					}
					
				}else{//se p tio da base tem cor preta
					
					if(filhoEsq(base.getPai(),base)) {//se a base é o filho a esquerda
	
						base.getPai().setCor(base.getPai().getCor() * (-1));//muda a cor do pai da base
						aux.setCor(aux.getCor() * (-1));//muda a cor do avô da base
						rotacaoDir(aux);//rotaciona no avô da base
						
			
					}else {//se a base é o filho da direita
						aux.setCor(aux.getCor() * (-1));//muda a cor do aux
						base.setCor(base.getCor() * (-1));//muda a cor da base
						rotacaoEsqDir(aux);//rotacao esquerda direita no avo da base
							
					}
				}
			}else {//se o pai da base é o filho a direita

				if(verificarCor(aux.getLeft()) == 1) {//se o tio da base tem cor vermelha
					base.getPai().setCor(base.getPai().getCor() * (-1));//muda a cor do pai da base
					aux.getLeft().setCor(aux.getLeft().getCor() * (-1));//muda a cor do tio da base
					
					if(aux != root) {//se o avô não for a raiz
						aux.setCor(aux.getCor() * (-1));//muda a cor do avô
					}
					
				}else{//se o tio da base tem cor preta
					
					if(filhoEsq(base.getPai(),base)) {//se a base é o filho a esquerda
						base.setCor(base.getCor() * (-1));//muda a cor da base
						aux.setCor(aux.getCor() * (-1));//muda a cor do avo da base
						rotacaoDirEsq(aux);//rotaciona direita esquerda no avô da base
						
					}else {//se a base é o filho da direita
						aux.setCor(aux.getCor() * (-1));//muda a cor do aux
						base.getPai().setCor(base.getPai().getCor() * -1);//muda a cor do pai da base
						rotacaoEsq(aux);//rotacao esquerda no avo da base
					
					}
				}
			}
			
		}
		if(root.getCor() != -1) {
			root.setCor(-1);
		}
	}//fim do metodo rubroNegraInserir
	
	//metodo de balanceamento rubro negra na exclusão
	private void rubroNegraExcluir(No base) {
		No aux;
		if(base == root) {
				 if(base.getRigth() != null) {//se tem filho a direita
					 aux = proximoSucessor(base.getRigth());//aux é igual ao proximo successor(nó substituto)
					 aux.setCor(-1);//muda cor de aux para preto
					
				 //se tem filho a esquerda e não tem filho a direita	 
				 }else if((base.getLeft() != null) && (base.getRigth() == null)) {
					 base.getLeft().setCor(-1);//muda cor do filho a esquerda para preto
					 
				 }
		}else {
			if(base.getCor() == -1) {//se a cor da base for preta
				aux = base.getPai();//aux é igual ao pai da base
			
				if(filhoEsq(aux,base)) {//se a base é o filho da esquerda
					if(verificarCor(aux.getRigth()) == -1) {//se a cor do irmão da base for preto
						//se a cor de um dos filhos do irmão for vermelha
						if((verificarCor(aux.getRigth().getLeft()) == 1) || (verificarCor(aux.getRigth().getRigth()) == 1)) {
							if(aux.getRigth().getRigth() != null) {//se o irmao tem um filho a direita
								aux.getRigth().getRigth().setCor(-1);//muda a cor do filho dele pra preto
								rotacaoEsq(aux);//rotaciona pra esquerda
								
							}else {//se o irmão não tem filho a direita
								aux.getRigth().getLeft().setCor(-1);//muda a cor do filho a esquera do irmão pra preto
								rotacaoDirEsq(aux);//faz rotacao direita esquerda
							}
						}else {//se os filhos do irmão tem cor preta
							aux.getRigth().setCor(1);//muda a cor do irmao pra vermelho
						
						}
					}else {//se a cor do irmão da base é vermelho
						//os dois filhos tem cor preta
						if(!((verificarCor(aux.getRigth().getLeft()) == 1) || (verificarCor(aux.getRigth().getRigth()) == 1))) {
							if(aux.getRigth().getRigth() != null) {//se aux tem um filho a direita
								rotacaoEsq(aux);//faz rotçãoo a esqerda
							
							}else {//se aux não tem filhoa direita
								rotacaoDirEsq(aux);//faz rotação a direita e esquerda
							}
						}
					}
				}else {//se a base é o filho da direita
					if(verificarCor(aux.getLeft()) == -1) {//se a cor do irmão da base for preto
						//se a cor de um dos filhos do irmão for vermelha
						if((verificarCor(aux.getLeft().getLeft()) == 1) || (verificarCor(aux.getLeft().getRigth()) == 1)) {
							if(aux.getLeft().getLeft() != null) {//se o irmao tem um filho a esquerda
								aux.getLeft().getLeft().setCor(-1);//muda a cor do filho dele pra preto
								rotacaoDir(aux);//rotaciona pra esquerda direita
								
							}else {//se o irmão não tem filho a esquerda
								aux.getRigth().getRigth().setCor(-1);//muda a cor do filho a direita do irmão pra preto
								rotacaoEsqDir(aux);//faz rotacao direita esquerda
								
							}
							
						}else {//se os filhos do irmão tem cor preta
						aux.getLeft().setCor(1);//muda a cor do irmao pra vermelho
						
						}
					}else {//se a cor do irmão da base é vermelho
						//os dois filhos do irmão da base tem cor preta
						if(!((verificarCor(aux.getLeft().getLeft()) == 1) || (verificarCor(aux.getLeft().getRigth()) == 1))) {
							if(aux.getLeft().getLeft() != null) {//se o irmão tem um filho a esquerda
								rotacaoDir(aux);//faz rotçãoo a direita
							
							}else {//se aux não tem filhoa direita
							rotacaoEsqDir(aux);//faz rotação a esquerda direita
							}
						}
					}
				}
			}else {//se a cor da base for vermelha
				if(base.getLeft() != null && base.getRigth() != null) {//se a base tem dois filhos
					aux = proximoSucessor(base.getRigth());//aux é igual ao sucessor da base
					
					if(aux == base.getRigth()) {//se aux é igual ao filho a direita da base
						base.getRigth().setCor(1);//muda a cor do filho a direita para vermelho
						
					}
				}else if(base.getLeft() != null && base.getRigth() == null) {//se a base tem so o filho a esquerda
					base.getLeft().setCor(1);//muda a cor do filho a direita para vermelho
					
				}else if(base.getLeft() == null && base.getRigth() != null) {//se abase tem so o filho a direita
					aux = proximoSucessor(base.getRigth());
					
					if(aux == base.getRigth()) {//se o aux é igual ao filho a direita da base
						base.getRigth().setCor(1);//muda a cor do filho a direita para vermelho
					}
				}
				
			}
		}
		if(root.getCor() != -1) {//se a riaz mudar a cor pra vermelho
			root.setCor(-1);//seta a cor da raiz pra preto
		}
	}//fim do metodo rubroNegraExclusao
	
	//metodo que verifica a cor do nó
	private int verificarCor(No base) {
		if(base != null) {
			return base.getCor();
		}
		return -1;
	}//fim do metodo verifica cor
}
