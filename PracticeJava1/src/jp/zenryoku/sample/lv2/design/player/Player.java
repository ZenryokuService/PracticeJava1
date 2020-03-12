/**
 * Copyright (c) 2019-present Math Kit JavaFX Library All rights reserved.
 * 
 * Redistribution and use in source and binary forms, with or without modification, are permitted provided that the following conditions are met:
 * Redistributions of source code must retain the above copyright notice, this list of conditions and the following disclaimer.
 * Redistributions in binary form must reproduce the above copyright notice, this list of conditions and the following disclaimer in the documentation and/or other materials provided with the distribution.
 * Neither the name Math Kit JavaFX Library nor the names of its contributors may be used to endorse or promote products derived from this software without specific prior written permission.
 */
package jp.zenryoku.sample.lv2.design.player;

import java.util.Map;

import javax.management.relation.Role;

import jp.zenryoku.sample.lv2.design.player.props.Body;
import jp.zenryoku.sample.lv2.design.player.props.Spirit;

/**
 * RPGのゲームをするプレーヤーを表す。
 * 
 * @author takunoji
 * 2020/03/12
 */
public abstract class Player {
	/** プレーヤーが操作する */
	protected boolean isPlayer;
	/** 生物である(true) */
	protected boolean isCreature;
	/** キャラクターID */
	protected long charactorId;
	/** 生命力(存在力) */
	protected int hp;
	/** 魔法力(呪術力) */
	protected int mp;
	/** レベル(職業別)<職業ID, レベル> */
	protected Map<String, Integer> levels;
	/** 精神を表す */
	protected Spirit sprit;
	/** 肉体を表す */
	protected Body body;
	/** キャラクターの役職(王様etc...) */
	protected Role role;

	/** どうぐコマンド */
	protected void items() {
	}
	
	/** しらべるコマンド */
	protected void check() {
	}


	/** はなすコマンド */
	protected void talk() {
	}

	/** つかうコマンド */
	protected void use() {
	}

	/** ぼうぎょコマンド */
	protected void difense() {
	}

	/** たたかうコマンド */
	protected int attack() {
		return 0;
	}
	////////////// Getter & Setter /////////////////
	/**
	 * @return the isPlayer
	 */
	public boolean isPlayer() {
		return isPlayer;
	}
	/**
	 * @param isPlayer the isPlayer to set
	 */
	public void setPlayer(boolean isPlayer) {
		this.isPlayer = isPlayer;
	}
	/**
	 * @return the isCreature
	 */
	public boolean isCreature() {
		return isCreature;
	}
	/**
	 * @param isCreature the isCreature to set
	 */
	public void setCreature(boolean isCreature) {
		this.isCreature = isCreature;
	}
	/**
	 * @return the charactorId
	 */
	public long getCharactorId() {
		return charactorId;
	}
	/**
	 * @param charactorId the charactorId to set
	 */
	public void setCharactorId(long charactorId) {
		this.charactorId = charactorId;
	}
	/**
	 * @return the hp
	 */
	public int getHp() {
		return hp;
	}
	/**
	 * @param hp the hp to set
	 */
	public void setHp(int hp) {
		this.hp = hp;
	}
	/**
	 * @return the mp
	 */
	public int getMp() {
		return mp;
	}
	/**
	 * @param mp the mp to set
	 */
	public void setMp(int mp) {
		this.mp = mp;
	}
	/**
	 * @return the levels
	 */
	public Map<String, Integer> getLevels() {
		return levels;
	}
	/**
	 * @param levels the levels to set
	 */
	public void setLevels(Map<String, Integer> levels) {
		this.levels = levels;
	}
	/**
	 * @return the sprit
	 */
	public Spirit getSprit() {
		return sprit;
	}
	/**
	 * @param sprit the sprit to set
	 */
	public void setSprit(Spirit sprit) {
		this.sprit = sprit;
	}
	/**
	 * @return the body
	 */
	public Body getBody() {
		return body;
	}
	/**
	 * @param body the body to set
	 */
	public void setBody(Body body) {
		this.body = body;
	}
	/**
	 * @return the role
	 */
	public Role getRole() {
		return role;
	}
	/**
	 * @param role the role to set
	 */
	public void setRole(Role role) {
		this.role = role;
	}
}
