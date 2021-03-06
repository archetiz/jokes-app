/*
 * Jokes API
 * API to get random jokes from a collection.
 *
 * OpenAPI spec version: 1.0.0
 * 
 *
 * NOTE: This class is auto generated by the swagger code generator program.
 * https://github.com/swagger-api/swagger-codegen.git
 * Do not edit the class manually.
 */

package kk.jokesapp.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * JokeResult
 */

public class JokeResult {
  @SerializedName("id")
  @Expose
  private Integer id = null;

  @SerializedName("type")
  @Expose
  private String type = null;

  @SerializedName("setup")
  @Expose
  private String setup = null;

  @SerializedName("punchline")
  @Expose
  private String punchline = null;

  public JokeResult id(Integer id) {
    this.id = id;
    return this;
  }

   /**
   * Get id
   * @return id
  **/
  public Integer getId() {
    return id;
  }

  public void setId(Integer id) {
    this.id = id;
  }

  public JokeResult type(String type) {
    this.type = type;
    return this;
  }

   /**
   * Get type
   * @return type
  **/
  public String getType() {
    return type;
  }

  public void setType(String type) {
    this.type = type;
  }

  public JokeResult setup(String setup) {
    this.setup = setup;
    return this;
  }

   /**
   * Get setup
   * @return setup
  **/
  public String getSetup() {
    return setup;
  }

  public void setSetup(String setup) {
    this.setup = setup;
  }

  public JokeResult punchline(String punchline) {
    this.punchline = punchline;
    return this;
  }

   /**
   * Get punchline
   * @return punchline
  **/
  public String getPunchline() {
    return punchline;
  }

  public void setPunchline(String punchline) {
    this.punchline = punchline;
  }

}

