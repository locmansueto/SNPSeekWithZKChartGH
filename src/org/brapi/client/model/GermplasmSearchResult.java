/*
 * BrAPI implementation for PIPPA
 * A first draft implementation of the breeding API
 *
 * OpenAPI spec version: 1.0.0
 * 
 *
 * NOTE: This class is auto generated by the swagger code generator program.
 * https://github.com/swagger-api/swagger-codegen.git
 * Do not edit the class manually.
 */


package org.brapi.client.model;

import java.util.Objects;
import com.google.gson.annotations.SerializedName;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import org.brapi.client.model.GermplasmResultData;
import org.brapi.client.model.Metadata;

/**
 * GermplasmSearchResult
 */
@javax.annotation.Generated(value = "io.swagger.codegen.languages.JavaClientCodegen", date = "2017-05-31T12:16:05.949+02:00")
public class GermplasmSearchResult {
  @SerializedName("metadata")
  private Metadata metadata = null;

  @SerializedName("result")
  private GermplasmResultData result = null;

  public GermplasmSearchResult metadata(Metadata metadata) {
    this.metadata = metadata;
    return this;
  }

   /**
   * Get metadata
   * @return metadata
  **/
  @ApiModelProperty(example = "null", value = "")
  public Metadata getMetadata() {
    return metadata;
  }

  public void setMetadata(Metadata metadata) {
    this.metadata = metadata;
  }

  public GermplasmSearchResult result(GermplasmResultData result) {
    this.result = result;
    return this;
  }

   /**
   * Get result
   * @return result
  **/
  @ApiModelProperty(example = "null", value = "")
  public GermplasmResultData getResult() {
    return result;
  }

  public void setResult(GermplasmResultData result) {
    this.result = result;
  }


  @Override
  public boolean equals(java.lang.Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    GermplasmSearchResult germplasmSearchResult = (GermplasmSearchResult) o;
    return Objects.equals(this.metadata, germplasmSearchResult.metadata) &&
        Objects.equals(this.result, germplasmSearchResult.result);
  }

  @Override
  public int hashCode() {
    return Objects.hash(metadata, result);
  }


  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class GermplasmSearchResult {\n");
    
    sb.append("    metadata: ").append(toIndentedString(metadata)).append("\n");
    sb.append("    result: ").append(toIndentedString(result)).append("\n");
    sb.append("}");
    return sb.toString();
  }

  /**
   * Convert the given object to string with each line indented by 4 spaces
   * (except the first line).
   */
  private String toIndentedString(java.lang.Object o) {
    if (o == null) {
      return "null";
    }
    return o.toString().replace("\n", "\n    ");
  }
  
}
