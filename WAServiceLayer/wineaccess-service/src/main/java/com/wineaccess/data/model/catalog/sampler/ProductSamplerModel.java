package com.wineaccess.data.model.catalog.sampler;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

import org.hibernate.annotations.NotFound;
import org.hibernate.annotations.NotFoundAction;
import org.hibernate.search.annotations.Analyze;
import org.hibernate.search.annotations.ContainedIn;
import org.hibernate.search.annotations.Field;
import org.hibernate.search.annotations.FieldBridge;
import org.hibernate.search.annotations.Fields;
import org.hibernate.search.annotations.Indexed;
import org.hibernate.search.annotations.IndexedEmbedded;
import org.hibernate.search.annotations.Store;

import com.wineaccess.data.model.EntityListener;
import com.wineaccess.data.model.catalog.product.ProductItemModel;
import com.wineaccess.data.model.common.MasterData;
import com.wineaccess.data.model.user.Persistent;

/**
 * @author gaurav.agarwal1
 *
 */
@Entity
@Table(name = "PRODUCT_SAMPLER")
@EntityListeners(EntityListener.class)
@NamedQueries({
	@NamedQuery(name = "ProductSamplerModel.getByProductAndSampler", query = "from ProductSamplerModel where productId.id = :productId and samplerId.id = :samplerId"),
	@NamedQuery(name = "ProductSamplerModel.getBySamplerId", query = "from ProductSamplerModel where samplerId.id = :samplerId"),
	@NamedQuery(name = "ProductSamplerModel.getById", query = "from ProductSamplerModel where id = :id")
})
@Indexed
public class ProductSamplerModel extends Persistent {

	private static final long serialVersionUID = -8661718259257124307L;

	@Id
	@Column(name = "ID")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Field(name = "id", analyze = Analyze.YES, store = Store.NO)
	private Long id;
	
	@ContainedIn
	@FieldBridge(impl = SamplerWineNameFieldBridge.class)
	@Fields( {
		@Field(name = "wineName", analyze = Analyze.YES, store = Store.NO),
		@Field(name = "wineNameSort", analyze = Analyze.NO, store = Store.NO)
	})
	@ManyToOne
	@JoinColumn(name = "PRODUCT_ID")
	private ProductItemModel productId;

	@ManyToOne
	@JoinColumn(name = "SAMPLER_ID")
	private SamplerModel samplerId;

	@Field(name = "wineSrpPrice", analyze = Analyze.YES, store = Store.NO)
	@Column(name = "SRP_PRICE")
	private Double srpPrice = 0.00;
	
	@IndexedEmbedded
	@NotFound(action = NotFoundAction.IGNORE)
	@ManyToOne
	@JoinColumn(name = "QUANTITY")
	private MasterData quantity;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public SamplerModel getSamplerId() {
		return samplerId;
	}

	public void setSamplerId(SamplerModel samplerId) {
		this.samplerId = samplerId;
	}

	public Double getSrpPrice() {
		return srpPrice;
	}

	public void setSrpPrice(Double srpPrice) {
		this.srpPrice = srpPrice;
	}
	
	public ProductItemModel getProductId() {
		return productId;
	}

	public void setProductId(ProductItemModel productId) {
		this.productId = productId;
	}

	public MasterData getQuantity() {
		return quantity;
	}

	public void setQuantity(MasterData quantity) {
		this.quantity = quantity;
	}

}
